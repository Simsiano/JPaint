package azioni;

import grafica.Grafica;
import helpers.Cursore;
import helpers.Cursore.Forma;
import helpers.Cursore.Tipo;

public class AzioniRDB {

	public void setCuroreMatita() {
		Cursore.setTipo(Grafica.getStrokeWidth(), Tipo.MATITA, Forma.TONDA);
	}

	public void setCursoreGomma() {
		Cursore.setTipo(Grafica.STROKEGOMMA, Tipo.GOMMA, Forma.TONDA);
	}

}
