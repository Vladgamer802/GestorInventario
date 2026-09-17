package model;



public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private int unidades;
    private int stock;
    private int categoria_id;

    public Producto(){}

    public Producto(int id, String nombre, String descripcion, int unidades, int stock, int categoria_id){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidades = unidades;
        this.stock = stock;
        this.categoria_id = categoria_id;
    }
    public Producto(String nombre, String descripcion, int unidades, int stock, int categoria_id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidades = unidades;
        this.stock = stock;
        this.categoria_id = categoria_id;
    }

    public int getId(){

        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public int getUnidades(){
        return unidades;
    }
    public void setUnidades(int unidades){
        this.unidades = unidades;
    }
    public int getStock(){
        return stock;
    }
    public void setStock(int stock){
        this.stock = stock;
    }
    public int getCategoria_id(){
        return categoria_id;
    }
    public void setCategoria_id(int categoria_id){
        this.categoria_id = categoria_id;
    }
}
