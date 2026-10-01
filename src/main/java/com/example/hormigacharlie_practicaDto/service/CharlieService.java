package com.example.hormigacharlie_practicaDto.service;

import com.example.hormigacharlie_practicaDto.dto.ConfiguracionDTO;
import com.example.hormigacharlie_practicaDto.dto.EstadoDTO;
import com.example.hormigacharlie_practicaDto.dto.SimulacionDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Logica:
//
// 🐜 Charlie encuentra miga
//       ↓
//deja feromona
//       ↓
//  gira derecha
//       ↓
//      avanza
//
//🐜 Charlie encuentra feromona
//       ↓
//  deja miga
//       ↓
//  gira izquierda
//       ↓
//     avanza


//ConfiguracionDTO
//       ↓
//datos iniciales
//
//EstadoDTO
//       ↓
//estado actual
//
//CharlieService
//       ↓
//REGLAS DE LA SIMULACIÓN
//
//tablero.html
//       ↓
//REPRESENTACIÓN VISUAL

@Service
public class CharlieService {

    public SimulacionDTO simular(ConfiguracionDTO configuracion) {

        //Creamos el DTO que representará toda la simulación.
        SimulacionDTO simulacion = new SimulacionDTO();

        //Creamos una lista para guardar los diferentes estados.
        List<EstadoDTO> estados = new ArrayList<>();

        //Creamos el estado actual de Charlie.
        EstadoDTO estado = new EstadoDTO();

        // Copiamos los datos iniciales de la configuración.
        estado.setPosicionX(configuracion.getPosicionX());
        estado.setPosicionY(configuracion.getPosicionY());
        estado.setDireccion(configuracion.getDireccion());
        estado.setDimension(configuracion.getDimension());
        estado.setMovimientos(0);

        // Creamos el terreno.

        //Obtiene la dimensión del terreno desde la configuración
        int dimension = configuracion.getDimension();

        //Crea una matriz cuadrada para representar el terreno
        int[][] terreno = new int[dimension][dimension];

        //Inicialmente todas las parcelas tienen una miga.

        //¿Qué hace cada for?
        //i recorre las filas.
        //
        //j recorre las columnas.
        //
        //terreno[i][j] = 1 coloca una miga en esa parcela.
        
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                terreno[i][j] = 1;
            }
        }


        //Guardamos el terreno dentro del DTO.
        estado.setTerreno(terreno);

        //Verificamos que accion realizar.
        //Charlie observa qué hay en su parcela
        //y aplica la regla correspondiente.
        ejecutarRegla(estado);

        //Comprobamos si necesita ampliar el terreno.
        if (necesitaAmpliacion(estado)) {
            ampliarTerreno(estado, configuracion.getAmpliacion());
        }

        //Charlie avanza.
        avanzar(estado);

        //Registramos el movimiento.
        estado.setMovimientos(1);


        //Guardamos una copia del estado después del primer movimiento.
        estados.add(copiarEstado(estado));

        //Guardamos todos los estados dentro de la simulación.
        simulacion.setEstados(estados);

        return simulacion;
    }

    private void girarDerecha(EstadoDTO estado) {

        if (estado.getDireccion().equals("ARRIBA")) {
            estado.setDireccion("DERECHA");

        } else if (estado.getDireccion().equals("DERECHA")) {
            estado.setDireccion("ABAJO");

        } else if (estado.getDireccion().equals("ABAJO")) {
            estado.setDireccion("IZQUIERDA");

        } else if (estado.getDireccion().equals("IZQUIERDA")) {
            estado.setDireccion("ARRIBA");
        }
    }

    private void girarIzquierda(EstadoDTO estado) {

        if (estado.getDireccion().equals("ARRIBA")) {
            estado.setDireccion("IZQUIERDA");

        } else if (estado.getDireccion().equals("IZQUIERDA")) {
            estado.setDireccion("ABAJO");

        } else if (estado.getDireccion().equals("ABAJO")) {
            estado.setDireccion("DERECHA");

        } else if (estado.getDireccion().equals("DERECHA")) {
            estado.setDireccion("ARRIBA");
        }
    }


    private void avanzar(EstadoDTO estado) {


        //Guardamos temporalmente la posición actual
        int x = estado.getPosicionX();
        int y = estado.getPosicionY();


        //ejm
        //x Y: 0 1 2
        //0
        //1
        //2
        if (estado.getDireccion().equals("ARRIBA")) {
            x--;

        } else if (estado.getDireccion().equals("DERECHA")) {
            y++;

        } else if (estado.getDireccion().equals("ABAJO")) {
            x++;

        } else if (estado.getDireccion().equals("IZQUIERDA")) {
            y--;
        }

        estado.setPosicionX(x);
        estado.setPosicionY(y);
    }

    //Calculamos cuánto crece por cada lado
    private void ampliarTerreno(EstadoDTO estado, int ampliacion) {

        //Sacamos del EstadoDTO el terreno que Charlie está usando actualmente.
        int[][] terrenoViejo = estado.getTerreno();

        //length nos dice cuántas filas tiene la matriz.
        //Si tenemos:
        //
        //int[5][5]
        //
        //entonces:
        //
        //dimensionVieja = 5
        int dimensionVieja = terrenoViejo.length;


        //Si:
        //
        //ampliacion = 4
        //
        //tenemos:
        //
        //4 / 2 = 2
        //
        //Así que agregamos:
        //
        //2 arriba
        //2 abajo
        //2 izquierda
        //2 derecha
        int aumentoPorLado = ampliacion / 2;


        //Por ejemplo:
        //
        //5 + 4 = 9
        //
        //Por lo tanto crearemos:
        //
        //9 × 9
        int dimensionNueva = dimensionVieja + ampliacion;

        //Creamos la matriz nueva/ el nuevo terreno
        int[][] terrenoNuevo = new int[dimensionNueva][dimensionNueva];

        //Las nuevas parcelas comienzan con migas.
        for (int i = 0; i < dimensionNueva; i++) {
            for (int j = 0; j < dimensionNueva; j++) {
                terrenoNuevo[i][j] = 1;
            }
        }

        //Copiamos el terreno anterior dentro del nuevo.
        for (int i = 0; i < dimensionVieja; i++) {
            for (int j = 0; j < dimensionVieja; j++) {

                terrenoNuevo[i + aumentoPorLado][j + aumentoPorLado] = terrenoViejo[i][j];
            }
        }

        //Actualizamos la posición de Charlie.
        estado.setPosicionX(estado.getPosicionX() + aumentoPorLado);
        estado.setPosicionY(estado.getPosicionY() + aumentoPorLado);

        //Guardamos el nuevo terreno.
        estado.setTerreno(terrenoNuevo);
        estado.setDimension(dimensionNueva);


    }

    //Verificamos si necesitamos ampliar el terreno
    private boolean necesitaAmpliacion(EstadoDTO estado) {

        int x = estado.getPosicionX();
        int y = estado.getPosicionY();

        int dimension = estado.getDimension();

        //Logica
        //Si el terreno es:
        //
        //5 × 5
        //
        //entonces:
        //
        //dimension = 5
        //
        //Pero las posiciones son:
        //
        //0 1 2 3 4
        //
        //Por eso la última posición es:
        //
        //dimension - 1
        //
        //o sea:
        //
        //5 - 1 = 4


        if (estado.getDireccion().equals("ARRIBA")) {
            return x == 0;

        } else if (estado.getDireccion().equals("DERECHA")) {
            return y == dimension - 1;

        } else if (estado.getDireccion().equals("ABAJO")) {
            return x == dimension - 1;

        } else if (estado.getDireccion().equals("IZQUIERDA")) {
            return y == 0;
        }

        return false;
    }

    private void cambiarMigaPorFeromona(EstadoDTO estado) {

        //obtenemos dónde está Charlie.
        int x = estado.getPosicionX();
        int y = estado.getPosicionY();

        //obtenemos el tablero.
        int[][] terreno = estado.getTerreno();

        //preguntamos:
        //"¿La parcela donde está Charlie tiene una miga?"
        //Si sí:
        //terreno[x][y] = 0; -> la convertimos en feromona.
        if (terreno[x][y] == 1) {
            terreno[x][y] = 0;
        }
    }

    private void cambiarFeromonaPorMiga(EstadoDTO estado) {

        int x = estado.getPosicionX();
        int y = estado.getPosicionY();

        int[][] terreno = estado.getTerreno();

        if (terreno[x][y] == 0) {
            terreno[x][y] = 1;
        }
    }

    //Metodo para saber que debe hacer charlie si encuentra una miga o feromona
    //                 CharlieService
    //                       │
    //                       ▼
    //                  simular()
    //                       │
    //                       ▼
    //                ejecutarRegla()
    //                 /           \
    //                /             \
    //             MIGA          FEROMONA
    //              ↓                ↓
    //      cambiar a 0       cambiar a 1
    //              ↓                ↓
    //      girar derecha     girar izquierda
    //              \                /
    //               \              /
    //                ▼            ▼
    //                   avanzar()
    private void ejecutarRegla(EstadoDTO estado) {

        int x = estado.getPosicionX();
        int y = estado.getPosicionY();

        int[][] terreno = estado.getTerreno();

        if (terreno[x][y] == 1) {

            //Charlie encontró una miga.
            cambiarMigaPorFeromona(estado);

            //Gira 90° a la derecha.
            girarDerecha(estado);

        } else {

            //Charlie encontró una feromona.
            cambiarFeromonaPorMiga(estado);

            //Gira 90° a la izquierda.
            girarIzquierda(estado);
        }
    }

    //Copiamos estado de DTO para poder mostrar los movimientos
    //Este metodo recibe un EstadoDTO y devuelve otro EstadoDTO.
    private EstadoDTO copiarEstado(EstadoDTO original) {

        EstadoDTO copia = new EstadoDTO();

        copia.setPosicionX(original.getPosicionX());
        copia.setPosicionY(original.getPosicionY());
        copia.setDireccion(original.getDireccion());
        copia.setMovimientos(original.getMovimientos());
        copia.setDimension(original.getDimension());

        int[][] terrenoOriginal = original.getTerreno();

        int filas = terrenoOriginal.length;
        int columnas = terrenoOriginal[0].length;

        int[][] terrenoCopia = new int[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                terrenoCopia[i][j] = terrenoOriginal[i][j];
            }
        }

        copia.setTerreno(terrenoCopia);

        return copia;
    }











}