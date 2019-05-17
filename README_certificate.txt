tu peux suivre la section "Certificate" de la doc d'installation de geoxygene :
http://ignf.github.io/geoxygene/documentation/developer/install.html
à l'étape 2, au lieu de la ligne donnée "keytool -v -alias mavensrv
-import -file forge-cogit.crt -keystore trust.jks", tu peux
directement mettre le certificat dans le keystore par défaut de java :
/etc/ssl/certs/java/cacerts

son mdp par défaut est "changeit"

Ce qui te donne une commande du genre :
"keytool -v -alias mavensrv -import -file forge-cogit.crt -keystore
/etc/ssl/certs/java/cacerts"

