# DISCLAIMER

**Do not use this software without prior written consent by the BFS to reproduction, processing, translation, storage, processing, reproduction of content in databases or other electronic media and systems as well as electronic exploitation.**

For further information visit : [BFS Legal](http://www.bfs.de/DE/service/impressum/impressum_node.html)

## Copyright

BfS endeavors to observe the copyrights of the graphics, sound documents, video sequences and texts used, to use graphics, sound documents, video sequences and texts created by itself, or to access license-free graphics, sound documents, video sequences and texts in all publications.

The contents published on these pages are subject to the German copyright and the right to protection of intellectual property. Trademarks and trademarks which may be protected by third parties are subject to the provisions of the respectively valid trademark law and the property rights of the respective registered owners. The mere naming of the trademark does not lead to the conclusion that trademarks are not protected by third-party rights.

The copyright for texts, pictures, graphics and other contents lies with the BfS, unless otherwise indicated. BfS also uses royalty-free images and graphics from the Pixabay image database, which are published under Creative Commons CC0 as a public domain or as a public domain.

Any use not permitted by the German Copyright and Performance Right or the relevant trademark law requires the express prior written consent of the BfS or the respective copyright owner. This applies, in particular, to the reproduction, processing, translation, storage, processing, reproduction of content in databases or other electronic media and systems as well as electronic exploitation. The unauthorized use of individual contents or complete pages is not permitted and punishable.

The representation of these pages in foreign frames (part of an HTML page) is only permitted with express prior written consent.

## Urheberrecht

Das BfS ist bestrebt, in allen Publikationen die Urheberrechte an den verwendeten Grafiken, Tondokumenten, Videosequenzen und Texten zu beachten, von ihm selbst erstellte Grafiken, Tondokumente, Videosequenzen und Texte zu nutzen oder auf lizenzfreie Grafiken, Tondokumente, Videosequenzen und Texte zurückzugreifen.

Die auf diesen Seiten veröffentlichten Inhalte unterliegen grundsätzlich dem deutschen Urheber- und Leistungsschutzrecht. Die gegebenenfalls durch Dritte geschützten Marken- und Warenzeichen unterliegen den Bestimmungen des jeweils gültigen Kennzeichenrechts und den Besitzrechten der jeweiligen eingetragenen Eigentümer. Allein aufgrund der bloßen Nennung ist nicht der Schluss zu ziehen, dass Markenzeichen nicht durch Rechte Dritter geschützt sind.

Das Copyright für Texte, Bilder, Grafiken und andere Inhalte liegt beim BfS, soweit dies nicht anders gekennzeichnet ist. Das BfS bedient sich auch lizenzfreier Bilder und Grafiken aus der Bilddatenbank Pixabay, die unter Creative Commons CC0 als Public Domain bzw. als gemeinfrei veröffentlicht sind.

Jede vom deutschen Urheber- und Leistungsschutzrecht oder vom einschlägigen Kennzeichenrecht nicht zugelassene Verwertung bedarf der ausdrücklichen vorherigen schriftlichen Zustimmung des BfS oder des jeweiligen Rechteinhabers. Dies gilt insbesondere für die Vervielfältigung, Bearbeitung, Übersetzung, Einspeicherung, Verarbeitung, Wiedergabe von Inhalten in Datenbanken oder anderen elektronischen Medien und Systemen sowie die elektronische Verwertung. Die unerlaubte Verwertung einzelner Inhalte oder kompletter Seiten ist nicht gestattet und strafbar.

Die Darstellung dieser Seiten in fremden Frames (Teilbereich einer HTML-Seite) ist nur mit ausdrücklicher vorheriger schriftlicher Zustimmung gestattet.


# BSF UV-Radiation Importer
Importer for the UV-Radiation Index of the Bundesministerium für Strahlenschutz (BFS) for Germany. See the data at [BFS UV-Index](http://www.bfs.de/DE/themen/opt/uv/uv-index/aktuell/aktuell_node.html)

## Run the Docker
```sh
$ docker run --env KAFKA_HOST=hostname:port --env KAFKA_TOPIC=topicName olib10/bsf-uv-importer
```

#### Mandatory enviroment variables:
- KAFKA_HOST
- KAFKA_TOPIC
