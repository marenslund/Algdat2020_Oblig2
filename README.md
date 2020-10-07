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

Jeg har brukt git til å dokumentere arbeidet mitt. Jeg har 13 commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.

I oppgaven har vi hatt følgende arbeidsfordeling:
* Maren har hatt hovedansvar for oppgave 1, 2, 3, 4, 5, 6, 8

# Beskrivelse av oppgaveløsning (maks 5 linjer per oppgave)

* Oppgave 1: Løste ved å implementere en for-løkke som traverserer lista a fra den siste noden og bakover til den første, 
for så å legge de til i en dobbelt lenket liste. Implementerte også en if-test som sjekker at nodene ikke er null, og hvis de er det,
blir de hoppet over, altså ikke lagt inn.
* Oppgave 2: Løste ved å først implementere toString() og omvendtString metodene. La til en StringBuilder i begge metodene, en if-test som sjekker om lista er tom.
Hvis ikke, legger den til nodene fra lista i strengen. Så implementerte jeg leggInn metoden
med en test som sjekker om lista er tom, og legger til verdien først. Hvis den ikke er tom, peker den på halen, og legger til verdien bakerst.

