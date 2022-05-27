import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author Lucas Castro
 */
public class Partida {

    private Equipe timeA;
    private Equipe timeB;

    private int timeAPontos = 0;
    private int timeBPontos = 0;

    private LocalTime horario = LocalTime.now();

    private Scanner scan = new Scanner(System.in);

    public void cadastrarPartida(){
        System.out.println("""
                Por favor, cadastre os dois times que irão participar do jogo.""");
        System.out.println("Insira o nome do primeiro time: ");
        timeA = new Equipe(scan.next());
        System.out.println("Insira o nome do segundo time: ");
        timeB = new Equipe(scan.next());
        System.out.println(" ");
    }

    public void iniciarPartida(){
        System.out.println("""
                --------------------------
                | A partida irá começar! |
                --------------------------
                """);

        String scoreboard = """
               ---------------------------------
               |        Placar atual           |
               | %s - %s                       
               | %d X %d                       
               ---------------------------------
                """.formatted(timeA.getNome(), timeB.getNome(), timeAPontos, timeBPontos);
        System.out.println(scoreboard);

        partidaEmAndamento(scoreboard);




    }

    private boolean partidaEmAndamento(String scoreboard){
        boolean loopControl = true;
        while(loopControl){


            marcarPonto();

            System.out.println("""
               ---------------------------------
               |        Placar atual           |
               | %s - %s                       
               | %d X %d                       
               |        Sets Ganhos            |
               | %s - %s                       
               | %d - %d                       
               ---------------------------------
                """.formatted(timeA.getNome(), timeB.getNome(),
                    timeAPontos, timeBPontos,
                    timeA.getNome(), timeB.getNome(),
                    timeA.getSetsGanhos(), timeB.getSetsGanhos()
                    ));

            ganharSet();


            if(ganhadorPartida()) {
                System.out.println("""
               ---------------------------------
               |        Sets Ganhos            |
               | %s - %s                       
               | %d - %d                       
               ---------------------------------
                        """.formatted(timeA.getNome(), timeB.getNome(),
                            timeA.getSetsGanhos(), timeB.getSetsGanhos()
                        ));
               loopControl = false;
               return true;
            }



        }

        System.out.println("oi");

        return false;
       }

    private void marcarPonto(){
           String pontuacao;
           boolean loopControl = true;
           do{
               System.out.println("Qual equipe deverá receber um ponto? [A/B]");
               pontuacao = scan.next();
               if (pontuacao.equalsIgnoreCase("A")) {
                   timeAPontos++;
               } else if (pontuacao.equalsIgnoreCase("B")){
                   timeBPontos++;
               }

               if(pontuacao.equalsIgnoreCase("A") || pontuacao.equalsIgnoreCase("B")){
                   loopControl = false;
               }
           }while(loopControl);

       }

    public Equipe ganharSet(){
        if(timeA.getSetsGanhos() + timeB.getSetsGanhos() < 4){
            return calcularDiferenca(25);
        }else {
            return calcularDiferenca(15);
        }

    }

    private Equipe calcularDiferenca(int maximoPontosSet){
        if( formulaDiferenca(timeA, timeAPontos, timeBPontos, maximoPontosSet) ) return timeA;
        if( formulaDiferenca(timeB, timeBPontos, timeAPontos, maximoPontosSet) ) return timeB;

        return null;
    }

    private boolean formulaDiferenca(Equipe timeX, int timeXPontos, int timeYPontos, int maximoPontosSet) {
        if ((timeXPontos >= maximoPontosSet) && ((timeXPontos - timeYPontos) >= 2)){
            if (verificaGanhador(timeX.getNome(), "o set")) {
                timeAPontos = 0;
                timeBPontos = 0;

                timeX.increaseSetsGanhos();
                return true;
            }
        }

        return false;
    }



    private boolean ganhadorPartida(){
        boolean resultado = false;
        if(timeA.getSetsGanhos() >= 3){
           resultado = verificaGanhador(timeA.getNome(), "a partida");
        } else if (timeB.getSetsGanhos() >= 3) {
            resultado = verificaGanhador(timeB.getNome(),"a partida");
        }

        return resultado;
    }

    private boolean verificaGanhador(String nomeGanhador, String nomeAcao){
        String winnerWarning = "O time %s ganhou %s";
        System.out.println(winnerWarning.formatted(nomeGanhador, nomeAcao));
        System.out.println("Está de acordo com a informação? [s/N]");
        String resultado = scan.next();
        if(resultado.equalsIgnoreCase("s")) return true;
        return false;
    }


    public LocalTime getHorario(){
        return horario;
    }

    public Equipe getTimeA() {
        return timeA;
    }

    public Equipe getTimeB() {
        return timeB;
    }

    public void setTimeA(Equipe timeA) {
        this.timeA = timeA;
    }

    public void setTimeB(Equipe timeB) {
        this.timeB = timeB;
    }

    public void setTimeAPontos(int timeAPontos) {
        this.timeAPontos = timeAPontos;
    }

    public void setTimeBPontos(int timeBPontos) {
        this.timeBPontos = timeBPontos;
    }
}
