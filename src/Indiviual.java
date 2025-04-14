public class Indiviual extends Client {
    private String cpf;

    public Indiviual(int id, String name, String address, String cpf) {
        super(id, name, address);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("CPF: " + cpf);
    }
}
