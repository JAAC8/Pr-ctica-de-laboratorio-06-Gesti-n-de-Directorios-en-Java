/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ups.edu.ec.controlador;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;


public class ControladorDirectorio {

    private String ruta;
    private File archivo;
    private File[] archivos;

    public ControladorDirectorio() {

    }

    public void crearDirectorio(String ruta, String nombre) {
        archivo = new File(ruta + File.separator + nombre);
        archivo.mkdir();
    }

    public void renombrarDirectorio(String ruta, String actual, String renombre) {
        archivo = new File(ruta + File.separator + actual);
        File nuevo = new File(ruta + File.separator + renombre);
        archivo.renameTo(nuevo);
    }

    public void eliminarDirectorio(String ruta, String eliminar) throws IOException {
        archivo = new File(ruta + File.separator + eliminar);
        if (archivo.isDirectory()) {
            archivos = archivo.listFiles();

            for (int i = 0; i < archivos.length; i++) {
                if (archivos[i].isDirectory()) {
                    eliminarDirectorios(archivos[i]);
                } else {
                    archivos[i].delete();
                }
            }
            archivo.delete();
        } else {

            archivo.delete();

        }

    }

    public List<String> listarArchivos(String ruta) {
        List<String> lista = new ArrayList<>();
        lista.clear();
        archivo = new File(ruta);
        archivos = archivo.listFiles();

        for (File elemento : archivos) {
            if (!elemento.isHidden()) {
                lista.add(elemento.getName());
            }

        }

        return lista;
    }

    public boolean comprobarRuta(String ruta) {
        archivo = new File(ruta);
        if (archivo.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean comprobarSiExiste(String ruta, String nombre) {
        archivo = new File(ruta + File.separator + nombre);
        if (archivo.exists()) {
            return true;
        } else {
            return false;
        }

    }

    public List<String> listarArchivosOcultos(String ruta) {
        List<String> listaArchivosOcultos = new ArrayList<>();
        listaArchivosOcultos.clear();
        archivo = new File(ruta);
        archivos = archivo.listFiles();

        for (File elemento : archivos) {
            if (elemento.isHidden() && elemento.isFile()) {
                listaArchivosOcultos.add(elemento.getName());
            }
        }

        for (File elemento : archivos) {
            if (elemento.isDirectory()) {
                File[] subdirectorios = elemento.listFiles();
//                for (File subelemento : subdirectorios) {
//                    if (subelemento.isHidden() && subelemento.isFile()) {
//                        lista.add(subelemento.getName());
//                    }
//
//                }

            }
        }

        return listaArchivosOcultos;
    }

    public List<String> listarDirectoriosOcultos(String ruta) {
        List<String> listaDirectoriosOcultos = new ArrayList<>();
        listaDirectoriosOcultos.clear();
        archivo = new File(ruta);
        archivos = archivo.listFiles();

        for (File elemento : archivos) {
            if (elemento.isHidden() && elemento.isDirectory()) {
                listaDirectoriosOcultos.add(elemento.getName());
            }
        }

        return listaDirectoriosOcultos;
    }

    public void eliminarDirectorios(File path) {
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                eliminarDirectorios(files[i]);
            } else {
                files[i].delete();
            }
        }
        path.delete();

    }
    

    public List<String> buscarPorNombre(String ruta, String nombre) {
        archivo = new File(ruta + File.separator + nombre);
        archivos = archivo.listFiles();
        List<String> listaNombre = new ArrayList<>();
        for (File archivo1 : archivos) {
            listaNombre.add(archivo1.getName());
        }

        return listaNombre;
    }

    public String devolverRuta(String ruta, String nombre) {
        archivo = new File(ruta + File.separator + nombre);

        return archivo.getAbsolutePath();
    }

    public boolean validarRuta(String ruta) {
        archivo = new File(ruta);
        if (archivo.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public String mostrarInformacion(String nombre, String ruta) {

        archivo = new File(ruta);
        archivos = archivo.listFiles();
        String informacion = "La informacion es:" + "\n";

        for (File elemento : archivos) {
            if (elemento.getName().equals(nombre)) {
                String path = "Path: ";
                path = path.concat(elemento.getAbsolutePath());
                informacion = informacion.concat("\n");
                informacion = informacion.concat(path);

                String tamaño = "Tamaño: ";
                long bytes = elemento.length();
                bytes = (bytes) / (1024);
                String caracter = String.valueOf(bytes);
                caracter = caracter.concat(" Kb");
                tamaño = tamaño.concat(caracter);
                informacion = informacion.concat("\n");
                informacion = informacion.concat(tamaño);

                //permisos de lectura y escritura
                String lectura = "Permisos de lectura: ";
                if (!elemento.canRead()) {
                    lectura = lectura.concat("No");
                } else {

                    lectura = lectura.concat("Si");
                }

                informacion = informacion.concat("\n");
                informacion = informacion.concat(lectura);

                String escritura = "Permisos de escritura: ";
                if (!elemento.canWrite()) {
                    escritura = escritura.concat("No");
                } else {
                    escritura = escritura.concat("Si");

                }

                informacion = informacion.concat("\n");
                informacion = informacion.concat(escritura);

                long lastModified = elemento.lastModified();

                String pattern = "yyyy-MM-dd hh:mm aa";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                Date lastModifiedDate = new Date(lastModified);

                String fecha = "Última modificación: ";
                fecha = fecha.concat(lastModifiedDate.toString());
                informacion = informacion.concat("\n");
                informacion = informacion.concat(fecha);
            }
        }

        return informacion;
    }

}
