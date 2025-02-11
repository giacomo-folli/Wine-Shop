Documentazionedelprogetto

# WineShop

1. Introduzione
2. Revision history
3. Requisiti funzionali
4. Casi d’uso
5. Progettazione del database
6. Class Diagrams
    6.1. Classi principali
7. Manuale
    7.1. Installazione, Configurazione e Utilizzo del sistema
    7.2. Installazione database
    7.3. Guida Utente
8. Test delle funzionalità

## Introduzione

Si richiede lo sviluppo di un sistema di gestione per un negozio online di vini, con tre tipi di utenti: clienti, impiegati e amministratori. Di seguito le funzionalità principali:
- Clienti: registrazione, visualizzazione e acquisto dei vini, consultazione dello storico ordini.
- Impiegati: preparazione ordini, gestione magazzino.
- Amministratori: panoramica dello stato del negozio, gestione impiegati.
Il negozio si avvarrà di un fornitore e di un corriere per rifornimento e spedizione. L'applicazione seguirà un'architettura client-server,con operazioni varianti in base all'account utente.

## Revision history
Nella seguente tabella riportiamole diverseversioni dellapresente documentazione,fino
all’ultimoaggiornamento.

|-|-|-|
|Versione|Data|Descrizione|
|1.0|03/04/2023|Comprensione dei requisiti informali assegnati e stesura dell'introduzione e dei requisiti funzionali.|
|1.1|05/04/2023|Pianificazione e organizzazione del lavoro. Compilazione dei diagrammi UML necessari.|
|1.2|19/04/2023|Progettazione del DB con schema concettuale e schema|

Documentazionedelprogetto
```
logico.
1.2.1 20/04/2023 Scritturadelleprimeclassiprincipalieprogrammazionedelle
interazionibasseconilServer
1.2 23/04/2023 Scrittura delle funzioni descritte nel UseCase Diagrame
aggiornamento delladocumentazionevistii nuovirequisiti
insorti
1.3 10/05/2023 Testdicomponenti,unità,sistemaeinterazioni
1.4 20/05/2023 AggiuntadelManualecompleto
1.5 10/06/2023 Correzionifinali.
```
## Requisitifunzionali

Di seguito, presentiamo una tabella che riassume i requisiti derivanti dal documento corrispondente. Questi requisiti descrivono le funzionalità globali che il sistema deve fornire. Ogni utente potrà accedere ad un sottoinsieme di tali funzionalità, a seconda del tipo di account a lui conferito.
```
ID Nome Stato Priorità
F1 Registrazionediunnuovocliente implemented high
F2 Login implemented high
F3 Logout implemented high
F4 Modificapassword implemented medium
F5 Visualizzazionesconti implemented low
F6 Visualizzazionevini implemented medium
F7 Ricercaconfiltri implemented low
F8 Inserimentovininelcarrello implemented high
F9 Acquistovininelcarrello implemented high
F10 Compilazioneproposted’acquisto implemented high
F11 Ricezioneordinidivendita implemented medium
F12 Visualizzazioneclienti implemented medium
F13 Riceverepropostediacquisto implemented high
F14 Creazioneordinidiacquisto implemented high
F15 Firmadigitalediordinidiacquisto implemented medium
F16 Ricezionedinotifichedimancanzadivini implemented high
F17 Cambiocredenzialicliente implemented medium
F18 Cambiocredenzialiimpiegato implemented medium
F19 Inviorichiestadisupporto implemented low
F20 Ricezionenotifiche implemented medium
F21 Cancellazioneaccountimpiegato implemented medium
F22 Registrazionenuovovino implemented high
F23 Aggiornamentoinfovini implemented medium
F24 Cancellazionevinidasistema implemented medium
F25 Creazionediunreportmensile implemented high
```
