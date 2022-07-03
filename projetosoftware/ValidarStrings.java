/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosoftware;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author dlnotari
 */
public class ValidarStrings {

    // substitui a vírgula por ponto em valor do tipo double
    public static String substituiVirgulaPorPonto(String aux) {

        if (aux.contains(",")) {
            aux = aux.replace(",", ".");
        }
        return aux;
    }

    // converter string para double
    public static double converteStringDouble(String aux) {
        return Double.parseDouble(aux);
    }

    // testar se tem virgula e converte ao mesmo tempo
    public static double converteStringDoubleETestaVirgula(String aux) {
        return converteStringDouble(substituiVirgulaPorPonto(aux));
    }

    // converter string para data
    public static Calendar converteStringParaData(String data) {
        // testa data
        if (data.isEmpty()) {
            return Calendar.getInstance();
        }

        // separa a data
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(2, 4));
        int ano = Integer.parseInt(data.substring(4, 8));

        // cria um objeto calendar
        Calendar datassss = Calendar.getInstance();

        // atribui a data        
        datassss.set(ano, mes - 1, dia);

        // retorna um objeto date        
        return datassss;
    }

    // converte data em string para calendar, depois para time e, enfim string
    // para carregar no combobox da JCalendar
    public static Date converteDataBDParaDataComboBox(String data) {
        // testa data
        if (data.isEmpty()) {
            return Calendar.getInstance().getTime();
        }

        // converte data
        Calendar datassss = converteStringParaData(data);

        // retorna data
        return datassss.getTime();
    }

    // pega o dia da data
    public static int getDiaData(Calendar data) {
        return data.get(Calendar.DAY_OF_MONTH);
    }

    // pega o dia da data
    public static int getDiaData(String data) {
        return converteStringParaData(data).get(Calendar.DAY_OF_MONTH);
    }

    // pega o mês da data
    public static int getMesData(Calendar data) {
        return data.get(Calendar.MONTH);
    }

    // pega o mês da data
    public static int getMesData(String data) {
        return converteStringParaData(data).get(Calendar.MONTH); //+1;
    }

    // pega o ani da data
    public static int getAnoData(Calendar data) {
        return data.get(Calendar.YEAR);
    }

    // pega o ani da data
    public static int getAnoData(String data) {
        return converteStringParaData(data).get(Calendar.YEAR);
    }

    // pega data de hoje
    public static String getDataHoje() {
        // pega a data de hoje
        Calendar aux = Calendar.getInstance();

        // separa a data
        int dia = getDiaData(aux);
        int mes = getMesData(aux);
        int ano = getAnoData(aux);

        // cria variável de string
        StringBuilder data = new StringBuilder();

        // adiciona o dia
        if (dia > 9) {
            data.append(dia);
        } else {
            data.append("0").append(dia);
        }

        // adiciona mês
        mes++;
        if (mes > 9) {
            data.append(mes);
        } else {
            data.append("0").append(mes);
        }

        // adiciona ano
        data.append(ano);

        // retorna data
        return data.toString();
    }

    // pega o dia da semana da data de hoje
    public static int getDiaSemana() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
    }

    // pega o dia da semana da data informada
    public static int getDiaSemana(Calendar data) {
        return data.get(Calendar.DAY_OF_WEEK) - 1;
    }

    // converte data em calendar para string
    public static String calendarToData(Calendar aux) {
        // separa a data
        int dia = getDiaData(aux);
        int mes = getMesData(aux);
        int ano = getAnoData(aux);

        // cria variável de string
        StringBuilder data = new StringBuilder();

        // adiciona o dia
        if (dia > 9) {
            data.append(dia);
        } else {
            data.append("0").append(dia);
        }

        // adiciona mês
        mes++;
        if (mes > 9) {
            data.append(mes);
        } else {
            data.append("0").append(mes);
        }

        // adiciona ano
        data.append(ano);

        // retorna data
        return data.toString();
    }

    // monta data no formato string
    public static String montarDataString(int dia, int mes, int ano) {
        // cria variável de string
        StringBuilder data = new StringBuilder();

        // adiciona o dia
        if (dia > 9) {
            data.append(dia);
        } else {
            data.append("0").append(dia);
        }

        // adiciona mês
        mes++;
        if (mes > 9) {
            data.append(mes);
        } else {
            data.append("0").append(mes);
        }

        // adiciona ano
        data.append(ano);

        // retorna data
        return data.toString();
    }

    /***
     * formata o double com duas casas decimais
     * @param valor
     * @return 
     */
    public static double formatarDouble(double valor) {
        return Double.valueOf(String.format(Locale.US, "%.2f", valor));
    }
}
