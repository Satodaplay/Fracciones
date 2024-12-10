public class Fractions {

    public static String toWords(String s) {

        String[][] diccionario = {
                {"0", "cero"},
                {"1", "un"},
                {"2", "dos"},
                {"3", "tres"},
                {"4", "quatre"},
                {"5", "cinc"},
                {"6", "sis"},
                {"7", "set"},
                {"8", "vuit"},
                {"9", "nou"},
                {"10", "deu"},
                {"11", "onze"},
                {"12", "dotze"},
                {"13", "tretze"},
                {"14", "catorze"},
                {"15", "quinze"},
                {"16", "setze"},
                {"20", "vint"},
                {"30", "trenta"},
                {"40", "quaranta"},
                {"50", "cinquanta"},
                {"60", "seixanta"},
                {"70", "setanta"},
                {"80", "vuitanta"},
                {"90", "noranta"},
                {"100", "cent"},
                {"1000", "mil"},
                {"1000000", "un milión"},
                {"-1", ""},
        };

        String[] partes = convertirInt(s);
        int denominador = Integer.parseInt(partes[1]);
        int numerador = Integer.parseInt(partes[0]);

        return procesarFraccion(diccionario, numerador, denominador);

    }
    /*
               _____     ____
             /      \  |  o |
            |        |/ ___\|
            |_________/
            |_|_| |_|_|

     */

    /*
        - primeraletraMayus() Lo que hace es convertir la primera letra del String que se le pase en mayuscula
          tiene un pequeño control de errores que deuvelve sirve por si el texto esta vacio.
     */
    public static String primeraletraMayus(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }

    /*
        - convertirInt() Sirve para que le pases un String y lo parta por "/" .
     */

    public static String[] convertirInt(String fraccio) {
        return fraccio.split("/");
    }

    /*
        - leerDiccionario() Es una funcion que su funcion es leer el String diccionario y el que se encarga de convertir
          el numero a letras.
     */

    public static int leerDiccionario(String[][] diccionario, int respuesta) {
        for (int i = 0; i < diccionario.length; i++) {
            if (Integer.parseInt(diccionario[i][0]) == respuesta) {
                return i;
            }
        }
        return -1;
    }

    /*
        - procesarFraccion() Se encarga principalmente de dividir la fraccion para tener partes completas, y pasa
          el resto a la funcion separarNum o a separarNumCompleto.
     */

    public static String procesarFraccion(String[][] diccionario, int numerador, int denominador) {

        int partesCompletas = 0;

        if (numerador >= denominador) {
            partesCompletas = numerador / denominador;
            numerador %= denominador;
        }

        while (numerador >= denominador) {
            numerador /= denominador;
        }

        String result = separarNumCompleto(diccionario, partesCompletas);

        if (partesCompletas == 0) {
            return separarNum(diccionario, numerador, denominador, numerador, partesCompletas);
        } else if (partesCompletas > 0 && numerador == 0) {
            return result;
        } else {
            return result + ", " + separarNum(diccionario, numerador, denominador, numerador, partesCompletas);
        }
    }

    /*
        - separarNumCompleto() Separa por unidades, decenas, centenas etc el numero que le pongamos, y se lo pasara a
          la funcion numerador para que sepa como tiene que escribir el numero.
     */

    public static String separarNumCompleto(String[][] diccionario, int respuesta) {

        int milionesim = 0;
        int milesimas = 0;
        int centesimas = 0;
        int decenas = 0;
        int unidades = 0;

        while (respuesta >= 1000000) {
            respuesta = respuesta - 1000000;
            milionesim++;
        }

        while (respuesta >= 10000) {
            respuesta = respuesta - 1000;
            milesimas++;
        }

        while (respuesta >= 1000) {
            respuesta = respuesta - 1000;
            milesimas++;
        }

        while (respuesta >= 100) {
            respuesta = respuesta - 100;
            centesimas++;
        }

        while (respuesta >= 10) {
            respuesta = respuesta - 10;
            decenas++;
        }

        unidades = respuesta;
        decenas = decenas * 10;

        String posicionQuinta = "";
        String posicionCuarta = "";
        String posicionTercera = "";
        String posicionSegunda = "";
        String posicionPrimera = "";

        String result = numerador(diccionario, milionesim, milesimas, centesimas, decenas, unidades, posicionQuinta, posicionCuarta, posicionTercera, posicionSegunda, posicionPrimera);

        result = primeraletraMayus(result);

        return result;
    }

    /*
        - separarNum() esta funcion hace los mismo que la anterior pero para el denominador y el numerador.
     */

    public static String separarNum(String[][] diccionario, int respuesta1, int respuesta2, int numerador, int partesCompletas) {

        int milionesimN = 0;
        int milesimasN = 0;
        int centesimasN = 0;
        int decenasN = 0;
        int unidadesN = 0;

        int milionesimD = 0;
        int milesimasD = 0;
        int centesimasD = 0;
        int decenasD = 0;
        int unidadesD = 0;

        while (respuesta1 >= 1000000) {
            respuesta1 = respuesta1 - 1000000;
            milionesimN++;
        }

        while (respuesta1 >= 10000) {
            respuesta1 = respuesta1 - 1000;
            milesimasN++;
        }

        while (respuesta1 >= 1000) {
            respuesta1 = respuesta1 - 1000;
            milesimasN++;
        }

        while (respuesta1 >= 100) {
            respuesta1 = respuesta1 - 100;
            centesimasN++;
        }

        while (respuesta1 >= 10) {
            respuesta1 = respuesta1 - 10;
            decenasN++;
        }

        while (respuesta2 >= 1000000) {
            respuesta2 = respuesta2 - 1000000;
            milionesimD++;
        }

        while (respuesta2 >= 10000) {
            respuesta2 = respuesta2 - 1000;
            milesimasD++;
        }

        while (respuesta2 >= 1000) {
            respuesta2 = respuesta2 - 1000;
            milesimasD++;
        }

        while (respuesta2 >= 100) {
            respuesta2 = respuesta2 - 100;
            centesimasD++;
        }

        while (respuesta2 >= 10) {
            respuesta2 = respuesta2 - 10;
            decenasD++;
        }

        unidadesN = respuesta1;
        decenasN = decenasN * 10;
        unidadesD = respuesta2;
        decenasD = decenasD * 10;

        if (unidadesN == 1 && unidadesD == 2 && decenasD == 0) {
            return "Un mig";
        } else if (unidadesN == 1 && unidadesD == 3 && decenasD == 0) {
            return  "Un terç";
        } else if (unidadesN == 1 && unidadesD == 4 && decenasD == 0) {
            return  "Un cuart";
        }

        String posicionQuinta = "";
        String posicionCuarta = "";
        String posicionTercera = "";
        String posicionSegunda = "";
        String posicionPrimera = "";

        String resultDeno = denominador(diccionario, milionesimD, milesimasD, centesimasD, decenasD, unidadesD, numerador, posicionQuinta, posicionCuarta, posicionTercera, posicionSegunda, posicionPrimera);
        String resultNumera = numerador(diccionario, milionesimN, milesimasN, centesimasN, decenasN, unidadesN, posicionQuinta, posicionCuarta, posicionTercera, posicionSegunda, posicionPrimera);

        if (partesCompletas > 0) {
            return resultNumera + " " + resultDeno;
        } else {
            resultNumera = primeraletraMayus(resultNumera);
        }

        return resultNumera + " " + resultDeno;
    }

    /*
        - numerador() es una funcion que sirve para poner todas las excepciones del numerador osea que si son miles ponga
          diez mil o con los veintes que ponga la i.
    */

    public static String numerador(String[][] diccionario, int milionesima,int milesimas,int centesimas, int decenas, int unidades, String posicionQuinta, String posicionCuarta, String posicionTercera, String posicionSegunda, String posicionPrimera){
        String vacio = "";
        milesimas = milesimas * 1000;
        boolean mil = true;

        if (milesimas != 1000 && centesimas == 0 && decenas == 0 && unidades == 0 && milionesima == 0) {
            milesimas = milesimas / 1000;
            posicionCuarta = diccionario[leerDiccionario(diccionario, milesimas)][1] + " mil·lèsim";
            mil = false;
        }
        if (mil && milesimas > 1000) {
            milesimas = milesimas / 1000;
            int num = 0;
            int cent = 0;
            if (milesimas > 9) {
                while (milesimas > 99) {
                    milesimas = milesimas - 100;
                    cent++;
                }

                while (milesimas > 9) {
                    milesimas = milesimas - 10;
                    num++;
                }
                num = num * 10;
                if (cent > 0 && num != 10 && milesimas != 5) {
                    posicionCuarta = diccionario[leerDiccionario(diccionario, cent)][1] + "-cents " + diccionario[leerDiccionario(diccionario, num)][1] + "-" + diccionario[leerDiccionario(diccionario, milesimas)][1] + " mil ";
                } else if (num != 10 && milesimas != 5) {
                    posicionCuarta = diccionario[leerDiccionario(diccionario, num)][1] + diccionario[leerDiccionario(diccionario, milesimas)][1] + " mil";
                } else if (num != 10) {
                    posicionCuarta = diccionario[leerDiccionario(diccionario, num)][1] + "-" + diccionario[leerDiccionario(diccionario, milesimas)][1] + " mil";
                } else {
                    posicionCuarta = diccionario[leerDiccionario(diccionario, num)][1] + " mil ";
                }
            } else {
                posicionCuarta = diccionario[leerDiccionario(diccionario, milesimas)][1] + " mil";
            }
            vacio = " ";
        }

        if (centesimas == 1) {
            centesimas = centesimas * 100;
            posicionTercera = diccionario[leerDiccionario(diccionario, centesimas)][1];
        } else if (centesimas > 1 && unidades == 2) {
            posicionTercera = diccionario[leerDiccionario(diccionario, centesimas)][1] + "-cents ";
        } else if (centesimas > 1) {
            posicionTercera = diccionario[leerDiccionario(diccionario, centesimas)][1] + "-cents";
        }

        if (decenas == 20 && unidades > 0 && unidades != 5) {
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1] + "-i-";
        } else if (decenas == 20 && unidades > 0) {
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1] + "-i";
        } else if (unidades != 5 && decenas > 20){
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1] + "-";
        } else if (decenas == 0) {
            posicionSegunda = "";
        } else {
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1];
        }

        if (decenas == 10 && unidades < 7 && unidades > 0) {
            decenas = decenas + unidades;
            unidades = -1;
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1];
        }

        if (unidades == 5 && decenas != 0) {
            posicionPrimera = "-" + diccionario[leerDiccionario(diccionario, unidades)][1];
        } else {
            posicionPrimera = diccionario[leerDiccionario(diccionario, unidades)][1];
        }

        if (unidades == 0) {
            posicionPrimera = "";
        }

        if (centesimas != 0 && decenas != 0 && unidades != 0) {
            vacio = " ";
        }

        return posicionCuarta + posicionTercera + vacio + posicionSegunda + posicionPrimera;
    }

    /*
        - denominador() es una funcion que sirve para poner todas las excepciones del denominador osea que si son miles
          ponga diez mil o con los veintes que ponga la i.
    */

    public static String denominador(String[][] diccionario, int milionesima, int milesimas, int centesimas, int decenas, int unidades, int numerador, String posicionQuinta, String posicionCuarta, String posicionTercera, String posicionSegunda, String posicionPrimera){

        milionesima = milionesima * 1000000;

        if (milionesima == 1000000 && numerador == 1 && milesimas == 0 && centesimas == 0 && decenas == 0 && unidades == 0) {
            posicionQuinta = "milionèsim";
        } else if (milionesima == 1000000 && milesimas == 0 && centesimas == 0 && decenas == 0 && unidades == 0) {
            posicionQuinta = "milionèsims";
        } else if (milionesima == 1000000) {
            posicionQuinta = "milió ";
        } else if (milionesima > 1000000) {
            milionesima = milionesima / 1000000;
            posicionQuinta = diccionario[leerDiccionario(diccionario, milionesima)][1] + " milions ";
        }


        milesimas = milesimas * 1000;
        String vacio = "";
        if (milesimas == 1000) {
            posicionCuarta = "mil" + " ";
        }
        if (milesimas == 1000 && numerador == 1 && centesimas == 0 && decenas == 0 && unidades == 0) {
            posicionCuarta = "mil·lèsim";
        }
        if (milesimas == 1000 && numerador != 1 && centesimas == 0 && decenas == 0 && unidades == 0) {
            posicionCuarta = "mil·lèsims";
        }

        boolean mil = true;

        if (milesimas != 1000 && centesimas == 0 && decenas == 0 && unidades == 0 && milionesima == 0) {
            milesimas = milesimas / 1000;
            posicionCuarta = diccionario[leerDiccionario(diccionario, milesimas)][1] + " mil·lèsim";
            mil = false;
        }
        if (mil && milesimas > 1000) {
            milesimas = milesimas / 1000;
            int num = 0;
            int cent = 0;
            if (milesimas > 9) {
                while (milesimas > 99) {
                    milesimas = milesimas - 100;
                    cent++;
                }

                while (milesimas > 9) {
                    milesimas = milesimas - 10;
                    num++;
                }
                num = num * 10;
                if (cent > 0 && num != 10 && milesimas != 5) {
                    posicionCuarta = diccionario[leerDiccionario(diccionario, cent)][1] + "-cents " + diccionario[leerDiccionario(diccionario, num)][1] + "-" + diccionario[leerDiccionario(diccionario, milesimas)][1] + " mil";
                } else if (num != 10 && milesimas != 5) {
                    posicionCuarta = diccionario[leerDiccionario(diccionario, num)][1] + diccionario[leerDiccionario(diccionario, milesimas)][1] + " mil";
                } else if (num != 10) {
                    posicionCuarta = diccionario[leerDiccionario(diccionario, num)][1] + "-" + diccionario[leerDiccionario(diccionario, milesimas)][1] + " mil";
                } else {
                    posicionCuarta = diccionario[leerDiccionario(diccionario, num)][1] + " mil";
                }
            } else {
                posicionCuarta = diccionario[leerDiccionario(diccionario, milesimas)][1] + " mil";
            }
            vacio = " ";
        }

        if (centesimas == 1) {
            centesimas = centesimas * 100;
            posicionTercera = diccionario[leerDiccionario(diccionario, centesimas)][1] + " ";
        }

        if (centesimas == 100 && numerador == 1) {
            posicionTercera = "centèsim";
        } else if (centesimas > 1 && centesimas != 100) {
            posicionTercera = diccionario[leerDiccionario(diccionario, centesimas)][1] + "-cents" + " ";
        }

        if (decenas == 20 && unidades > 0 && unidades != 5) {
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1] + "-i-";
        } else if (decenas == 20 && unidades > 0) {
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1] + "-i";
        } else if (unidades != 5 && decenas > 20 && unidades != 0){
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1] + "-";
        } else if (decenas > 10 && decenas < 17 && numerador == 1 && unidades == 0 || decenas > 20 && decenas < 100 && numerador == 1 && unidades == 0) {
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1].substring(0, diccionario[leerDiccionario(diccionario, decenas)][1].length() - 1) + "è";
        } else if (unidades == 0 && decenas != 20 && numerador == 1 && decenas != 10) {
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1].substring(0, diccionario[leerDiccionario(diccionario, decenas)][1].length() - 1) + "è";
        }  else if (unidades == 0 && decenas != 20 && decenas != 10) {
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1].substring(0, diccionario[leerDiccionario(diccionario, decenas)][1].length() - 1) + "ens";
        } else if (unidades == 0 && numerador == 1 && decenas != 10 ) {
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1] + "è";
        } else if (unidades == 0 && decenas != 10) {
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1] + "ens";
        } else if (decenas == 10 && unidades > 7 && unidades <= 9) {
            posicionSegunda = "di";
        } else if (decenas == 10 && unidades == 7) {
            posicionSegunda = "dis";
        } else if (decenas == 10 && unidades == 0 && numerador == 1) {
            posicionSegunda = "dècim";
        } else if (decenas == 10 && unidades == 0) {
            posicionSegunda = "dècims";
        } else if (decenas == 0) {
            posicionSegunda = "";
        } else {
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1];
        }

        if (decenas == 10 && unidades < 7 && unidades > 0 && numerador == 1) {
            decenas = decenas + unidades;
            unidades = -1;
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1].substring(0, diccionario[leerDiccionario(diccionario, decenas)][1].length() - 1) + "è";
        }
        if (decenas == 10 && unidades < 7 && unidades > 0 ) {
            decenas = decenas + unidades;
            unidades = -1;
            posicionSegunda = diccionario[leerDiccionario(diccionario, decenas)][1].substring(0, diccionario[leerDiccionario(diccionario, decenas)][1].length() - 1) + "ens";
        }
        if (numerador == 1 && !posicionSegunda.equals(diccionario[leerDiccionario(diccionario, decenas)][1].substring(0, diccionario[leerDiccionario(diccionario, decenas)][1].length() - 1) + "è")) {
            posicionPrimera = diccionario[leerDiccionario(diccionario, unidades)][1] + "è";
        } else if (numerador != 1 && unidades == 4) {
            posicionPrimera = diccionario[leerDiccionario(diccionario, unidades)][1].substring(0, diccionario[leerDiccionario(diccionario, unidades)][1].length() - 1) + "ens";
        } else if (numerador != 1 && unidades != -1 && !posicionSegunda.equals(diccionario[leerDiccionario(diccionario, decenas)][1].substring(0, diccionario[leerDiccionario(diccionario, decenas)][1].length() - 1) + "è")) {
            posicionPrimera = diccionario[leerDiccionario(diccionario, unidades)][1] + "ens";
        }

        if (unidades == 5 && numerador == 1) {
            posicionPrimera = "-cinquè";
        } else if (unidades == 5 && decenas == 0 ) {
            posicionPrimera = "cinquens";
        } else if (unidades == 5) {
            posicionPrimera = "-cinquens";
        }

        if (unidades == 9 && numerador == 1) {
            posicionPrimera = "novè";
        } else if (unidades == 9) {
            posicionPrimera = "novens";
        }

        if (decenas == 0) {
            posicionSegunda = "";
        }

        if (unidades == 0) {
            posicionPrimera = "";
        }

        return posicionQuinta + posicionCuarta + vacio + posicionTercera + posicionSegunda + posicionPrimera;
    }
}