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

| Versione | Data       | Descrizione |
| :---     |  :----:    |  ---:  |
| 1.0      | 03/04/2023 | Comprensione dei requisiti informali assegnati e stesura dell'introduzione e dei requisiti funzionali.|
| 1.1      | 05/04/2023 | Pianificazione e organizzazione del lavoro. Compilazione dei diagrammi UML necessari.|
| 1.2      | 19/04/2023 | Progettazione del DB con schema concettuale e schema logico.|
| 1.2.1    | 20/04/2023 | Scritturadelle prime classi principali e programmazione delle interazioni basse con il Server|
| 1.2      | 23/04/2023 | Scrittura delle funzioni descritte nel Use Case Diagram e aggiornamento della documentazione visti i nuovi requisiti insorti|
| 1.3      | 10/05/2023 | Test di componenti, unità, sistema e interazioni|
| 1.4      | 20/05/2023 | Aggiunta del Manuale completo|
| 1.5      | 10/06/2023 | Correzioni finali.|


## Requisitifunzionali

Di seguito, presentiamo una tabella che riassume i requisiti derivanti dal documento corrispondente. Questi requisiti descrivono le funzionalità globali che il sistema deve fornire. Ogni utente potrà accedere ad un sottoinsieme di tali funzionalità, a seconda del tipo di account a lui conferito.

|ID     | Nome | Stato | Priorità |
| :--   | :--: | :--:  | :-- |
|F1     | Registrazione di un nuovo cliente | implemented | high|
|F2 |Login |implemented |high|
|F3 |Logout| implemented| high|
|F4 |Modifica password |implemented| medium|
|F5 |Visualizzazione sconti| implemented| low|
|F6 |Visualizzazione vini |implemented |medium|
|F7 |Ricerca con filtri |implemented| low|
|F8 |Inserimento vini nel carrello| implemented| high|
|F9 |Acquisto vini nel carrello |implemented |high|
|F10|Compilazione proposte d’acquisto| implemented| high|
|F11|Ricezione ordini di vendita | implemented| medium|
|F12|Visualizzazione clienti |implemented |medium|
|F13|Ricevere proposte di acquisto| implemented| high|
|F14|Creazione ordini di acquisto |implemented |high|
|F15|Firma digitale di ordini di acquisto| implemented| medium|
|F16|Ricezione di notifiche di mancanza di vini |implemented |high|
|F17|Cambio credenziali cliente |implemented |medium|
|F18|Cambio credenziali impiegato| implemented| medium|
|F19|Inviorichiestadisupporto |implemented |low|
|F20|Ricezione notifiche| implemented |medium|
|F21|Cancellazione account impiegato| implemented| medium|
|F22|Registrazione nuovo vino |implemented |high|
|F23|Aggiornamento info vini| implemented| medium|
|F24|Cancellazione vini da sistema |implemented |medium|
|F25|Creazione di un report mensile | implemented| high|

