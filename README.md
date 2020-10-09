# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 

# Krav til innlevering

Se oblig-tekst for alle krav. Oppgaver som ikke oppfyller følgende vil ikke få godkjent:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* Ingen debug-utskrifter
* Alle testene som kreves fungerer (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet

# Arbeidsfordeling

Oppgaven er levert av følgende studenter:
* Maren Spongsveen Lund, s344057, s344057@oslomet.no

Jeg har brukt git til å dokumentere arbeidet mitt. Jeg har 39 commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.

I oppgaven har vi hatt følgende arbeidsfordeling:
* Maren har hatt hovedansvar for oppgave 1, 2, 3, 4, 5, 6, 8

# Beskrivelse av oppgaveløsning (maks 5 linjer per oppgave)

* Oppgave 1: Løste ved å implementere en for-løkke som traverserer lista a fra den siste noden og bakover til den første, 
for så å legge de til i en dobbelt lenket liste. Implementerte også en if-test som sjekker at nodene ikke er null, og hvis de er det,
blir de hoppet over, altså ikke lagt inn.
* Oppgave 2: Løste ved å først implementere toString() og omvendtString metodene. La til en StringBuilder i begge metodene, en if-test som sjekker om lista er tom.
Hvis ikke, legger den til nodene fra lista i strengen. Så implementerte jeg leggInn metoden
med en test som sjekker om lista er tom, og legger til verdien først. Hvis den ikke er tom, peker den på halen, og legger til verdien bakerst.
* Oppgave 3: Løste ved å først lage den private metoden finnNode som tar enn en indeks. deretter leter den etter noden med den indeksen, og starter
foran/bakerst basert på hvilken som er nærmest (som blir sjekket med en if-test). Deretter implementerte jeg hent metoden som bruker finnNode, samt oppdater
som oppdaterer verdien på indeks plassen med den gitte nye verdien.
så implementerte jeg subListe metoden som lager en ny liste med de verdiene hentet utifra "fra" og "til" parameterene. Metoden oppretter en ny liste
og setter inn de nye verdiene bakerst. Metoden bruker finnNode for å finne verdien, og leggInn for å legge til
* Oppgave 4: Løste ved å først implementere indeksTil med en for-loop som looper gjennom 
lista og sjekker om verdien finnes. bruker node.neste for å sjekke neste node i lista. Så implementerte jeg 
inneholder metoden som sjekker om indeksTil(verdi) returnerer -1 eller ikke. Returnerer false om -1 returneres, og true ellers.
* Oppgave 5: Løste ved å først legge inn sjekk for ugyldige verdier, for så å legge inn sjekker
for når verdien skulle legges inn bakerst, forrest, og når lista var tom. Til slutt implementerte jeg
en for-loop som finner node.neste basert på indeksen den nye verdien skal legges inn i, for så å legge inn verdien på riktig plass.
* Oppgave 6: Løste ved å implementere fjern(indeks) med if-tester som sjekker for når den skal fjerne siste
node, første node og node mellom. Så implementerte jeg fjern(verdi) med if-tester som sjekker lista for om verdien som skal fjernes
ligger først, eller ikke finnes. Så en else for når verdien som skal fjernes ligger mellom to noder
* Oppgave 8: Løste ved å først implementere next() metoden med if-tester som sjekker for exceptions og returnerer denne sin verdi
samt setter pekeren til neste verdi. Implementerte så iterator metodene som returnerer hver sine instanser av iteratorklassen. Til slutt
implementerte jeg DobbeltLenketListeIterator konstruktøren som satte denne til noden gitt av indeks, ved hjelp av finnNode() metoden

