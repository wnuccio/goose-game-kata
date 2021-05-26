###
- wrappare la collezione di movimenti in PlayerOnTurn in un oggetto
    a sè stante e riscrivere il test del metodo present
    in modo che sia più specifico
- spostare la costruzione di Board in configurazione, 
    gestendo il "nome" delle posizioni oltre la 63 come un concetto di presentazione

### Provare il packaging 'by feature' ricordando che 
- una feature non è uno use case ma è un concetto più generico che può essere
    - una entity (es. player, movement, game)
    - un aspetto (es. rules)
    - un'azione (es. rollmove)
    - un'astrazione (es. interpreter)
    - un concetto comune ad altre feature

- in questo approccio tutto sta in 'domain', non ci sono distinzioni di layer;
    se due 'feature' hanno qualcosa in comune, questa è a sua volta una 'feature' che va in un 
    pacakge separato
- la distinzione tra domain e 'adapters' ci può ancora stare, perchè in domain
siamo "dentro" l'applicazione, e quindi domain è diviso in feature, 
  mentre fuori domain non c'è un "layer" bensì un package per ogni adapter diverso
  (e quindi database, console di io per esempio).
  Il punto cruciale, però, è che ad esempio "database" non è un layer,
  cioè non è un posto dove finiscono tutte le classi di mapping tra dominio e 
  db, che invece sono ciascuna dove deve stare, cioè ciascuna vicino alla sua
  corrispondente classe di dominio, o ciascuna nella "feature" più adatta.
  Invece in "database" ci sono sono classi di utilità generale, es. un Driver di db,
  che è accessibile dal dominio tramite un'interfaccia che crea un po' di astrazione.

- l'approccio più semplice è mettere tutto in un unico package molto grosso e poi 
    scorporare un pacakge alla volta

- semplicità: se un package è troppo piccolo bisogna accorparlo; questo può abbassare la coesione
    di un package, portando a classi che non collaborano ma sono "dello stesso tipo"
    tuttavia riduce l'accoppiamento (es. invece di avere package separati per ogni rule,
    mettere tutte le rule insieme con il loro processo dentro un unico package)
    in questo caso si può giustificare la cosa col concetto di common-reuse, alternativo 
    a quello di common-closure: le classi non cambiano insieme (ogni regola è aggiunta o tolta 
  indipendentemente dalle altre, e segue regole proprie) ma tuttavia vengono riusate insieme
  (le rule si applicano sempre tutte)

- non ci sono più layer, tuttavia il concetto di architettura esagonale rimane,
    in quanto 'database' o 'inputoutput' possono essere considerate 'feature';
    è importante però scegliere per ogni package qual è il concetto di  
    raggruppamento che sta alla sua base, se quello di coesione (closure) o di riuso comune:
    nel caso di un package Database non avremo il concetto di riuso comune, 
    perchè non avremo qui tutti i repository, che saranno invece ciascuno
    nel suo package di feature specifico; 
    potremo invece avere una classe di utilità generale che permette di connettersi 
    al db o una che permette di scrivere query facilmente; le tipiche classi 
    che finiscono nel famigerato "common" o "utils"
  
- il concetto di layer scompare, ma bisogna tenere in qualche modo quello di sotto-package,
    individuando per esso un significato: perché creare un package dentro un altro 
    quando tra i due package non c'è nessuna relazione stabilita?
    - non ci può essere una dipendenza: altrimenti un package da cui dipendono molti altri
        dovrebbe essere contemporaneamente dentro tutti questi altri
      
- un'idea: l'insieme dei package è un albero, quindi una struttura gerarchica,
con una radice e delle foglie; la radice è certamente 'app' cioè l'intera applicazione;
  cosa siano le foglie non è chiaro ma dentro app ci sarà sicuramente Main
  e magari 'config' visto che 'config' usa tutto il resto;   