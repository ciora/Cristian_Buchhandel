Der Buchhandel ist in 2 Ansichten und 2 Dialogen aufgeteilt. Die zwei ansichten sind die Home Ansicht und die Warenkorb Ansicht. Die 2 Dialoge sind die Detail-Dialoge und das Purchase-Dialog.

![](./images/Home.jpg)

Wie in der Abbildung gesehen werden kann, besteht die Home-Ansicht aus einer Komponente wo die Filter und der Button zur Navigation gegeben sind.

Die Bücher werden in einem Datascroller angezeigt. Hier wurde darauf geschaut das paging betrieben wird. Da das sehr wichtig ist bei großen Datenmengen, da wenn alles auf einmal geladen wird, dies dazu führen kann das sich die Web-Applikation aufhängt.

Wird ein Genre ausgewählt werden alle Bücher ausgegeben welche diesem Genre angehören. Es kann gleichzeitig auch nach dem Titel gefiltert werden danach werden alle Bücher ausgegeben welche diesem Genre angehören und welche den String enthalten, welcher eingeben wurde.

![](./images/filteredBooks.jpg)

Wird auf dem Button mit der Lupe gedrückt kommt man zur Detail Ansicht des Buches wo noch mehr Informationen über dieses Buch geliefert werden.

![](./images/detailsDialog.jpg)

Neben der Lupe gibt es einen Button mit, welchem das Buch zu dem Warenkorb hinzugefügt wird.

Im Warenkorb angelangt sieht man wider die Lupe, mit welcher die Details angeschaut werden können. Daneben gibt es ein plus und ein minus. Mit diesen kann die Anzahl von Büchern, welche gekauft werden wollen verändert werden. Drückt man jedoch oft genug minus und das Ergebnis wird 0, so wird das Buch aus dem Warenkorb entfernt. Es kann aber natürlich auch mit dem Remove-Button entfernt werden.

![](./images/warenkorb.jpg)

Drüber gibt es zwei Buttons. Mit dem Home-Button gelangt man zurück zur Home-Ansicht. Wird jedoch auf dem Purchase-Button gedrückt, taucht ein Dialog auf, wo die Daten wo die Bücher geliefert werden sollen einzugeben sind. Wird auf dem Comfirm-Button gedrückt wird der Warenkorb gelehrt und es kann weitergeshopt werden.

![](./images/purchase.jpg)

Die Bücher werden dann in die Entity "OpenDelivery" dementsprechend oft gespeichert. Will zum Beispiel einer ein Buch 3 mal kaufen wird dieses 3 mal in die Datenbank gespeichert.

![](./images/bookPurchasesDb.jpg)
