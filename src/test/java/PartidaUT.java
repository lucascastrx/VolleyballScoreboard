import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class PartidaUT {


    @Test
    public void givenCorrectScoreThenSetWinner(){
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream("s\n".getBytes()));

        Equipe timeA = new Equipe("AFK");
        Equipe timeB = new Equipe("CFG");

        /*************************************/



        Partida partida = new Partida();
        partida.setTimeA(timeA);
        partida.setTimeB(timeB);

        partida.setTimeAPontos(25);


        Equipe ganhador = partida.ganharSet();


        System.setIn(stdin);
        Assertions.assertEquals(timeA, ganhador);
    }

    @Test
    public void givenWrongScoreThenNullWinner(){
        Equipe timeA = new Equipe("AFK");
        Equipe timeB = new Equipe("CFG");

        Partida partida = new Partida();
        partida.setTimeA(timeA);
        partida.setTimeB(timeB);

        partida.setTimeAPontos(18);


        Equipe ganhador = partida.ganharSet();


        Assertions.assertEquals(null, ganhador);
    }
}
