const express = require('express');
const bodyParser = require('body-parser');
const morgan = require('morgan');
const cors = require('cors');
const {Client} = require('pg');

const app = express();
app.disable('x-powered-by');
app.use(cors());
app.use(bodyParser.json({}));
app.use(morgan('dev'));


const client = new Client({
	connectionString: 'postgres://ztiuuvpd:rs0KSwq8kJyOF3ze8XOM3P8yvBbucJgv@horton.elephantsql.com:5432/ztiuuvpd'
});

client.connect()
	.then(() => {
		console.log('[' + new Date().toISOString() + '] Connect to Postgres OK ');
		const server = app.listen(process.env.ALWAYSDATA_HTTPD_PORT, process.env.ALWAYSDATA_HTTPD_IP, function () {
                         console.log('Example app started!'+process.env.ALWAYSDATA_HTTPD_PORT)
                       })
	})
	.catch(err => {
		console.log(err);
	});


app.get('/incidents', async(req, res) => {
	try {
		const result = await client.query('SELECT * FROM "Incidents"');
		console.log("incidents:  " + result.rows[0].message);
		res.status(200).json(result.rows);
	}
	catch (err) {
		res.status(500).json(err);
	}
});

app.post('/postIncident', async (req, res) => {
	console.log("post");
	try {
		var json = req.body;


		var query = "INSERT INTO incident " +
			"(titre, description, type, urgence, date, heure, duree, etat, localisation, idAuteur)" +
			" VALUES ('" + json['titre'] + "','" + json['description'] + "', '" + json['categorie'] + "', "
			+ json['urgence'] + ", '" + json['date'] + "', '" + json['heure'] + "', 5, 'Nouveau', '"
			+ json['localisation'] + "', '" + json['idauteur'] + "' )";
		const result = await client.query(query);


		const result2 = await client.query("SELECT idincident FROM INCIDENT WHERE titre = '" + json["titre"] + "' AND description = '" + json["description"] + "'");
		var idincidentcreated = (result2.rows[0].idincident);

		for (let destinataire of json["destinataires"]) {
			var someQuery = "INSERT INTO allocation (idincident, idpersonne) VALUES ('" + idincidentcreated + "', '" + destinataire + "')";
			const resultFinalQuery = await client.query(someQuery);
		}


		res.status(200).json(result.rows);
	}
	catch (err) {
		res.status(500).json(err);
		console.log(err)
	}
});
