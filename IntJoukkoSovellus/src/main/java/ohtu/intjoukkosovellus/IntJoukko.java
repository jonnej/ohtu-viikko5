
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukuJoukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        lukuJoukko = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        lukuJoukko = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;

    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        lukuJoukko = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == lukuJoukko.length) {
            int[] taulukkoOld = lukuJoukko.clone();
            lukuJoukko = new int[alkioidenLkm + kasvatuskoko];
            kopioiTaulukko(taulukkoOld, lukuJoukko);
        }
        if (!kuuluu(luku)) {
            lukuJoukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJoukko[i]) {
               return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukuJoukko[i]) {
                lukuJoukko[i] = lukuJoukko[alkioidenLkm-1];
                lukuJoukko[alkioidenLkm-1] = 0;
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        String tulostus = "{";
        if (alkioidenLkm == 0) {
            return "{}";
        }
        for (int i = 0; i < alkioidenLkm-1; i++) {
            tulostus += lukuJoukko[i] + ", ";
        }
        return tulostus + lukuJoukko[alkioidenLkm-1] + "}";
    }

    public int[] toIntArray() {
        int[] taulukko = new int[alkioidenLkm];
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = lukuJoukko[i];
        }
        return taulukko;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteTaulukko = new IntJoukko();
        int[] aTaulukko = a.toIntArray();
        int[] bTaulukko = b.toIntArray();
        for (int i = 0; i < aTaulukko.length; i++) {
            yhdisteTaulukko.lisaa(aTaulukko[i]);
        }
        for (int i = 0; i < bTaulukko.length; i++) {
            yhdisteTaulukko.lisaa(bTaulukko[i]);
        }
        return yhdisteTaulukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausTaulukko = new IntJoukko();
        int[] aTaulukko = a.toIntArray();
        int[] bTaulukko = b.toIntArray();
        for (int i = 0; i < aTaulukko.length; i++) {
            for (int j = 0; j < bTaulukko.length; j++) {
                if (aTaulukko[i] == bTaulukko[j]) {
                    leikkausTaulukko.lisaa(bTaulukko[j]);
                }
            }
        }
        return leikkausTaulukko;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotusTaulukko = new IntJoukko();
        int[] aTaulukko = a.toIntArray();
        int[] bTaulukko = b.toIntArray();
        for (int i = 0; i < aTaulukko.length; i++) {
            erotusTaulukko.lisaa(aTaulukko[i]);
        }
        for (int i = 0; i < bTaulukko.length; i++) {
            erotusTaulukko.poista(i);
        }
 
        return erotusTaulukko;
    }
        
}