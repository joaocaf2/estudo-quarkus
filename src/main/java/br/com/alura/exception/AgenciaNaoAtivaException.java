package br.com.alura.exception;

public class AgenciaNaoAtivaException extends RuntimeException {

    public AgenciaNaoAtivaException(String message){
        super(message);
    }
}
