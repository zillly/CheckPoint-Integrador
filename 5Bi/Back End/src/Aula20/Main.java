package Aula20;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {
    //Você deverá manter os dados de uma empresa que tem um CNPJ e Razão Social junto com a lista de seus funcionários em um arquivo. Os funcionários possuem nome, sobrenome, documento de identificação (RG ou CPF) e salário.
    //Sugerimos que utilize o método Main para instanciar uma empresa com 4 funcionários e armazená-los em um arquivo da empresa. Em seguida, recupere do arquivo, a empresa, que também deverá ter seus 4 funcionários.
    //
    //Se você chegou até aqui, parabéns! Se você ainda tiver tempo e quiser  continuar praticando, deixamos para você mais um desafio.
    public static void main(String[] args) throws FileNotFoundException {

        List<Empresa> empresas = new ArrayList<>();
        List<Funcionario> funcionarios = new ArrayList<>();


        Funcionario funcionario1 = new Funcionario("gilmar", "Miranda", 12, 100.0);
        Funcionario funcionario2 = new Funcionario("gilmar2", "Miranda2", 12, 150.0);
        Funcionario funcionario3 = new Funcionario("gilmar3", "Miranda3", 12, 160.0);
        Funcionario funcionario4 = new Funcionario("gilmar5", "Miranda4", 12, 170.0);
        funcionarios.add(funcionario1);
        funcionarios.add(funcionario2);
        funcionarios.add(funcionario3);
        funcionarios.add(funcionario4);

        Empresa empresa = new Empresa(1201, "?", funcionarios);
        System.out.println(empresa);
        empresas.add(empresa);

        try (OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream("empresa.txt"), StandardCharsets.UTF_8)) {

            os.write(empresas.toString());


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Empresa> empresaList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream("empresa.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            empresaList = (List<Empresa>) ois.readObject();

        }catch (FileNotFoundException e) {
            System.out.println("ERRO" + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERRO" + e.getMessage());
        }

        for (Empresa empresa1 : empresaList) {
            System.out.println(empresa1.getFuncionarios());
        }
    }



}
