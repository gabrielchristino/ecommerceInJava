public class LegalEntity extends Client {
    private int cnpj;

    public LegalEntity(int id, String name, String address, int cnpj) {
        super(id, name, address);
        this.cnpj = cnpj;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("CNPJ: " + cnpj);
    }
}
