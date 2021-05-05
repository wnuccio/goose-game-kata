- correggere l'esecuzione degli AT:
    c'è un effetto memoria dovuto al fatto
  che l'Applicazione ricorda la posizione di "Pippo" dal test precedente
  
- aggiungere il Presenter anche allo use case di AddPlayer

- spostare la logica che verifica la vittoria (magic number 63)
    su Players
  
- verificare che Players sia usato sempre come mockato, o riflettere
    su come testare le situazioni in cui si vuole che un player
    non parta dall'inizio
  
- simmetricamente al Movement per il Presenter, creare 
    degli oggetti Command di input per gli useCase