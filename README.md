# BSF UV-Radiation Importer
Importer for the UV-Radiation Index of the Bundesministerium für Strahlenschutz (BFS) for Germany. See the data at [BSF UV-Index](http://www.bfs.de/DE/themen/opt/uv/uv-index/aktuell/aktuell_node.html)

## Run the Docker
```sh
$ docker run --env KAFKA_HOST=hostname:port aardila/bsf-uv-importer
```

#### Mandatory enviroment variables:
- KAFKA_HOST