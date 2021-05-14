Rivedere i pattern
- Application Runner: non è più statico e non controlla
    l'unicità dell'applicazione; è un rappresentante dell'applicazione
    lato test, può esporre metodi di reset e stop e, cosa importante,
    wrappa l'Application Driver e lo usa dopo aver lanciato l'applicazione
    nel caso in cui quest'ultima restituisca un output speciale iniziale
    per segnalare che essa è partita correttamente
  
- Base Acceptance Test: assicura che l'Application Runner sia unico per tutti i 
    test, salvandolo in un field static con inizializzazione lazy;
    controlla lo stato dell'Application Runner per capire se invocare di nuovo run
    
- Crash Detector: il meccanismo viene mantenuto nell'Application Runner,
    in quanto è lui che controlla l'uscita dal main, per motivi normali
    o per crash; poi il runner espone lo stato dell'applicazione 
    e il Base Acceptance Test usa questo stato
  
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
      