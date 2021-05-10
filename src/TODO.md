Input/Output
- usare una Queue invece di una list nella SystemIoForTest
  
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
      