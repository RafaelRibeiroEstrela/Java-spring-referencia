package com.example.springwebexample.utils;

public class CpfUtil {

    public static String removerMascaraCpf(String cpf) {
        cpf = cpf.replace("-", "");
        cpf = cpf.replace(".", "");
        cpf = cpf.trim();
        return cpf;
    }
}
