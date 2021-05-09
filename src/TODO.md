Ripartenza in caso di errore
- risolvere il problema: il fallimento di un AT per un'eccezione determina il crash dell'app
  e il fallimento a cascata di tutti gli altri
- definire un input di errore specifico (es. error) e creare un caso di test
 di doppio AT (senza derivare dal base, ma inviando all'applicazione
  un input di errore seguito da uno buono)
  
Input/Output
- accorpare per semplicità i due boundary (InputOutput),
  creare una classe reale apposita e spostarla nel package boundary
- rinominare la classe di test Shared Memory e renderla privata al package
- usare una Queue invece di una list nella SystemIoForTest
- nel main, eliminare il comando "exit" e gestire invece anche l'output:
  solo in modalità test segnalare che l'applicazione è partita/in errore ecc...

Dice
- separare il concetto di Dice come oggetto immutabile che rappresenta una coppia di valori
dal DiceRoller, che è una factory di Dice; solo quest'ultima è iniettabile 

Struttura
- provare una alberatura di package gerarchica
   root (contiene solo Main)
     |_ config (contiene Application e il suo Builder)
       |_ boundary (infrastruttura, tutti gli adapter)
         |_ use case
           |_ domain
  
  in questo modo il contenimento sta ad indicare
  "dipende da", e inoltre mima la struttura concentrica della CA;

- ogni layer conterrà poi dei sotto-package, ma in questo caso il 
  package non ha più il significato di "layer" (v. più sotto)

- con questa struttura, quando c'è all'interno di un layer del codice
  comune a più classi, ovvero a più sotto-package, si può optare tra:
  - metterlo nel sotto-package più attinente e far dipendere gli altri da questo
  - metterlo in un sotto-package comune
  - spostarlo nel layer immediatamente più interno

- distinguere tra i due concetti:
  - package come 'layer': contiene classi allo stesso livello di astrazione
    (es. usecase) che non necessariamente collaborano; ad esempio
    le classi di infrastruttura, come l'input e l'output (es. su db)
    non collaborano affatto
  - package come 'unità di incapsulamento': è caratterizzato da una certa
    coesione, cioè rappresenta un'astrazione significativa e contiene classi che
    - collaborano tra loro in modo stretto (es. il package "Move Player"
      contiene il comando Move, il Player, le eccezioni relative ecc..)
    - non collaborano ma sono alternative tra loro (es. un'interfaccia
      che rappresenta una policy con le sue possibili implementazioni)

Reset Service
- realizzare il reset come caso a parte gestito solo in modalità test, 
  eliminando anche lo use case e il test relativo (ma NON il test di accettazione)


Use Case, parsing e dispatching
- spostare la logica di parsing in infrastructure
- rappresentare la logica di parsing e dispatching come una chain of responsibility:
    ogni anello della catena è un oggetto che contiene 
    un parser, uno use case e il relativo commmand;
    l'oggetto interroga il parser: se trova il comando giusto a inizio linea allora
    completa il parsing costruendo l'intero command e passandolo allo usecase, 
    altrimenti delega all'anello successivo;
    l'ultimo anello della catena non fa nulla (ignora l'input) o lancia un errore, a scelta
- gestire separatamente i vari use case e rimuovere la loro interfaccia di base

Test
- semplificare la costruzione di MoveCommand, eliminando i metodi privati nel test
