import java.io.File

fun main(args: Array<String>) {

    if(args[0] == "--help") {

        println("Welcome to wc")
        println("This is a tool to perform some basic analysis on a txt file")
        println("To use it simply run it as kotlinc wc.kt <text-file> <flags>\n")
        println("Flags\tUse")
        println("-c\t\tReads the size of the file and prints it to the console")
        println("-l\t\tPrints the number of lines in the given text file")
        println("-w\t\tPrints the number of words present in the file")
        println("-m\t\tPrints the number of characters in the file")
        return
    }

    if(args.isEmpty()) {
        println("wc: invalid number of arguments")
        println("use --help to learn more")
        return
    }

    val flags : ArrayList<String> = ArrayList()
    var fileName = ""

    try {
        for(arg in args) {

            if(arg.contains(".txt")) {
                fileName = arg
            }
            else if(arg.contains('-')) {
                flags.add(arg.substring(1))
            }
            else {
                throw Exception("incorrect syntax")
            }

        }

        if(fileName.isEmpty()) {
            throw Exception("file not given")
        }

    }
    catch (e : Exception) {
        print("incorrect command given")
        println("wc: ${e.message}")
    }



    val fileReader = File("src/wc/$fileName")

    var output = ""

    if(flags.isEmpty()) {
        flags.add("l")
        flags.add("w")
        flags.add("c")
    }

    for(flag in flags) {

        when(flag) {
            "c" -> output += "${fileReader.readBytes().size}\t"
            "l" -> output += "${fileReader.readLines().size}\t"
            "w" -> output += "${wordCounter(fileReader)}\t"
            "m" -> output += "${fileReader.readText().length}\t"
        }

    }

    output += fileName
    println(output)


}

fun wordCounter(fileReader: File) : Int {
 return  Regex("""(\s+|(\r\n|\r|\n))""").findAll(fileReader.readText().trim()).count() + 1
}