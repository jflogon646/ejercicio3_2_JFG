/**
 * Clase Persona
 * Contiene varias propiedades, a las que no se le puede acceder desde fuera.
 * Contiene métodos para calcular el IMC de la persona y si es mayor de edad
 *
 */

class Persona() {
    private val DNI: String
    private var edad: Int = 0
        /* Setter para edad */
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("La edad no puede ser menor que 0.")
            } else {
                field = value
            }
        }
    private var nombre: String = ""
    private var peso: Float = 70.0F
        /* Setter para peso */
        set(value) {
            if (value < 0.0F) {
                throw IllegalArgumentException("El peso no puede ser menor a 0.0.")
            } else {
                field = value
            }
        }
    private var altura: Float = 1.70F
        /* Setter para altura */
        set(value) {
            if (value < 0.0F) {
                throw IllegalArgumentException("La altura no puede ser menor de 0.0.")
            }
            else {
                field = value
            }
        }
    private var sexo: Char = 'H'
        /* Setter para sexo */
        set(value) {
            if (value == 'H' || value == 'M') {
                field = value
            } else {
                throw IllegalArgumentException("El sexo debe ser H o M.")
            }
        }

    /* Constructor con los parámetros nombre, edad y sexo, el resto por defecto. */
    constructor(p_nombre: String, p_edad: Int, p_sexo: Char) : this() {
        this.nombre = p_nombre
        this.edad = p_edad
        this.sexo = p_sexo
    }

    /* Constructor con 5 parámetros */
    constructor(p_nombre: String, p_edad: Int, p_peso: Float, p_altura: Float, p_sexo: Char) : this() {
        this.nombre = p_nombre
        this.edad = p_edad
        this.peso = p_peso
        this.altura = p_altura
        this.sexo = p_sexo
    }


    /* Init de la clase en el que se genera el DNI del objeto Persona y se comprueba el sexo introducido. */
    init {
        DNI = generaDNI()
        compruebaSexo()
    }

    /* Método para calcular el IMC, devuelve un Int que usaremos en una función externa
    * a la clase en la que devolvemos si está en su peso ideal.
    * Este método está pensado para usarse en conjunto con la función pesoIdeal. */
    fun calculaIMC(): Int{
        var imc = peso / (altura * altura)
        return when {
            imc < 20.0 -> { -1 }
            imc in 20.0..25.0 -> { 0 }
            imc > 25.0 -> { 1 }
            else -> { 2 }
        }
    }

    /* Método que devuelve un booleano para conocer si la persona es mayor de edad o no.
    * Pensado para usarse en conjunto con la función mayorEdad */
    fun esMayorDeEdad(): Boolean {
        return edad >= 18
    }


    /* Muestra la información del objeto Persona. */
    override fun toString(): String {
        val _sexo: String = if (sexo == 'H') {
            "Hombre"
        } else {
            "Mujer"
        }
        return "INFORMACIÓN DE PERSONA\nDNI: \"$DNI\"\nNombre: \"$nombre\"\nEdad: $edad \nPeso: $peso\nAltura: $altura\nSexo: $_sexo"
    }

    /* Método que comprueba si la variable sexo está bien (es H o M), y si no es así, la modifica. */
    private fun compruebaSexo() {
        if (sexo != 'H' || sexo != 'M') {
            sexo = 'H'
        }
    }

    /* Método para generar un DNI para el objeto Persona. El primer número no puede ser 0. */
    private fun generaDNI(): String {
        var dni: String =  "${(1..9).random()}"
        var check: Boolean = false
        while (!check) {
            dni += (0..9).random()
            if (dni.length == 7) {
                dni += ('A'..'Z').random()
                check = true
            }
        }
        return dni
    }
}

/* Función que crea un objeto persona. Dependiendo del valor del parámetro booleano, utiliza un constructor u otro. */
fun creaPersona(baliza: Boolean): Persona {
    var persona: Persona
    print("Introduzca un nombre: ")
    var nombre: String = readLine()!!.toString() ?: ""
    print("Introduzca una edad: ")
    var edad: Int = readLine()!!.toInt() ?: 0
    print("Introduzca un peso: ")
    var peso: Float = readLine()!!.toFloat() ?: 0.0F
    print("Introduzca una altura: ")
    var altura: Float = readLine()!!.toFloat() ?: 0.0F
    print("Introduzca un sexo: ")
    var sexo: Char = (readLine()!! ?: 'H') as Char
    persona = if (baliza) {
        Persona(nombre,edad,sexo)
    } else {
        Persona(nombre,edad,peso,altura,sexo)
    }
    return persona
}

/* Función pensada para usarse en conjunto con persona.calcularIMC().
*  Devuelve una cadena de texto indicando si la persona está en su peso ideal o no. */
fun pesoIdeal(imc: Int): String {
    return when (imc) {
        -1 -> { "Esta persona esta por debajo de su peso ideal(IMC por debajo de 20.0)." }
        0 -> { "Esta persona esta en su peso ideal(IMC entre 20.0 y 25.0)" }
        1 -> { "Esta persona esta por encima de su peso ideal(IMC por encima de 25.0)." }
        else -> { "Error. No se puede calcular IMC." }
    }
}

/* Función pensada para usarse en conjunto con el método esMayorDeEdad.
* Devuelve una cadena de texto indicando si la persona es mayor de edad o no.
* Recibe como parámetro el resultado del método. */
fun mayorEdad(resultado: Boolean): String {
    return if (resultado) {
        "Esta persona es mayor de edad."
    }
    else {
        "Esta persona es menor de edad."
    }
}

/* Clase ejecutable */
fun main() {
    var persona1 = creaPersona(true)
    var persona2 = creaPersona(false)
    var persona3 = Persona()

    pesoIdeal(persona1.calculaIMC())
    pesoIdeal(persona2.calculaIMC())
    pesoIdeal(persona3.calculaIMC())

    mayorEdad(persona1.esMayorDeEdad())
    mayorEdad(persona2.esMayorDeEdad())
    mayorEdad(persona3.esMayorDeEdad())

    println(persona1)
    println(persona2)
    println(persona3)




}