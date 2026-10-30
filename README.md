# Wine Shop

**Sistema gestionale per un'enoteca online sviluppato in Java.**

Wine Shop è un'applicazione client-server progettata per la gestione di un'attività di vendita online di vini. Il sistema gestisce i principali processi aziendali di un e-commerce, dall'acquisto da parte dei clienti alla gestione del magazzino e degli ordini ai fornitori.

Il progetto affronta la progettazione e lo sviluppo di un'applicazione software articolata, con diversi ruoli utente, funzionalità dedicate e processi interconnessi.

## Funzionalità

Il sistema prevede tre tipologie di utenti, ciascuna con specifiche funzionalità e responsabilità.

### Cliente
- Registrazione e autenticazione.
- Ricerca dei vini tramite filtri.
- Visualizzazione degli sconti disponibili.
- Gestione del carrello e acquisto dei prodotti.
- Consultazione dello storico degli acquisti.
- Modifica delle credenziali di accesso.
- Invio di richieste di assistenza.

### Dipendente
- Gestione del magazzino e delle informazioni sui vini.
- Gestione degli ordini ricevuti dai clienti.
- Preparazione di proposte d'acquisto per i fornitori.
- Creazione e firma digitale degli ordini d'acquisto.
- Ricezione di notifiche relative alle scorte in esaurimento.
- Consultazione delle informazioni sui clienti.

### Amministratore
- Gestione degli account dei dipendenti.
- Supervisione delle attività del negozio.
- Gestione del catalogo dei vini.
- Generazione di report mensili sull'andamento dell'attività.

## Architettura

L'applicazione adotta un'architettura **client-server**, organizzando le funzionalità in base al ruolo e ai permessi dell'utente autenticato.

Il sistema modella inoltre le interazioni con soggetti esterni, tra cui fornitori e corrieri, rappresentando il flusso completo delle principali operazioni commerciali.

## Progettazione e sviluppo

Il progetto copre diverse fasi del ciclo di sviluppo del software:

- **Analisi dei requisiti:** raccolta, analisi e formalizzazione dei requisiti funzionali.
- **Progettazione UML:** modellazione del sistema attraverso diagrammi UML.
- **Progettazione del database:** definizione del modello concettuale e logico dei dati.
- **Programmazione orientata agli oggetti:** progettazione e implementazione dei componenti applicativi in Java.
- **Architettura client-server:** progettazione della comunicazione tra le componenti dell'applicazione.
- **Testing:** test unitari, di componente, di sistema e di integrazione.
- **Documentazione:** produzione della documentazione tecnica e generazione della Javadoc.

## Tecnologie

- **Java** – linguaggio principale per lo sviluppo dell'applicazione.
- **UML** – analisi dei requisiti e modellazione del sistema.
- **Javadoc** – documentazione del codice sorgente.

## Documentazione

Il repository include ulteriore documentazione relativa al progetto:

- [Documentazione tecnica](WineShop_DOCUMENTATION.pdf) – requisiti, specifiche funzionali e progettazione del sistema.
- [Javadoc](javadoc/) – documentazione generata delle API e delle componenti del progetto.

## Obiettivi del progetto

Wine Shop nasce come progetto di Software Engineering con l'obiettivo di applicare i principi di progettazione e sviluppo del software alla realizzazione di un'applicazione gestionale completa.

L'attenzione non è rivolta soltanto all'implementazione delle singole funzionalità, ma anche alla modellazione dei requisiti, all'organizzazione delle componenti software e alla gestione delle interazioni tra utenti e processi aziendali.
