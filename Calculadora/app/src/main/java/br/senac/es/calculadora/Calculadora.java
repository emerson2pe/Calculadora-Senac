package br.senac.es.calculadora;

public class Calculadora {
    //Class publica

    private double operando;
    private double operandoAnterior;
    private String operadorAnterior;
    private double memoria;
    //4 variaveis

    public Calculadora() {
        //metodo construtor
        operando = 0;
        operandoAnterior = 0;
        operadorAnterior = "";
        memoria = 0;
        //4 variaveis recebendo valores

    }

    public double getOperando() {
        //metodo
        return operando;
        //metodo com retorno no caso 0
    }

    public void setOperando(double operando) {
        this.operando = operando;
    }


    //metodo privado, vazio que verifica se existe essa operação no caso se existe +, - etc.
    private void realizarOperacaoAnterior() {
        if (!operadorAnterior.equals("")) {
            if (operadorAnterior.equals("+")) {
                operando = operandoAnterior + operando;
            } else if (operadorAnterior.equals("-")) {
                operando = operandoAnterior - operando;
            } else if (operadorAnterior.equals("*")) {
                operando = operandoAnterior * operando;
            } else if (operadorAnterior.equals("/")) {
                if (operando != 0)
                {
                    //essa condição evita divisão por zero
                    operando = operandoAnterior / operando;
                }
            }
        }
    }

    //metodo public, vazio que faz a operação usados na condiçoes
    public void realizarOperacao(String op) {
        if (op.equals("%")) {
            operando = (operandoAnterior * operando) / 100;
        } else if (op.equals("+/-")) {
            operando = -operando;
        } else if (op.equals("C")) {
            operando = 0;
            memoria = 0;
            operadorAnterior = "";
        } else {
            realizarOperacaoAnterior();
            operadorAnterior = op;
            operandoAnterior = operando;
        }
    }
    //metodo public, vazio metodo de manipulação das operaçoes
    public void realizarOperacaoDeMemoria(String opm) {
        if (opm.equals("mc")) {
            memoria = 0;
        } else if (opm.equals("m+")) {
            memoria += operando;
        } else if (opm.equals("m-")) {
            memoria -= operando;
        } else if (opm.equals("mr")) {
            operando = memoria;
        }
    }
}

