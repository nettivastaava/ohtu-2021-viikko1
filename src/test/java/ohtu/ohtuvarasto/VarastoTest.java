package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktorilleEiVoiAntaaNegatiivistaArvoa() {
        varasto2 = new Varasto(-1);
        
        assertEquals(0.0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldoEiVoiYlittaaTilavuutta() {
        varasto2 = new Varasto(10, 11);
        
        assertEquals(varasto2.getTilavuus(), varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktorinAlkusaldoTasmaa() {
        varasto2 = new Varasto(10, 5);
        
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktorilleEiVoiAntaaNegatiivistaArvoa2() {
        varasto2 = new Varasto(-1, -1);
        
        assertEquals(0.0, varasto2.getSaldo(), vertailuTarkkuus);
        assertEquals(0.0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tilavuuttaEiVoidaYlittaa() {
        varasto.lisaaVarastoon(11);
        
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaYliSaldon() {
        varasto.lisaaVarastoon(5);
        
        assertEquals(varasto.otaVarastosta(6), 5, vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaNegatiivistaMaaraa() {
        assertEquals(varasto.otaVarastosta(-1), 0.0, vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(-1);
        
        assertEquals(varasto.getSaldo()-1, 0, vertailuTarkkuus);
    }
    
    @Test
    public void toStringTulostaaOikein() {
        assertEquals(varasto.toString(), "saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu());
    }

}