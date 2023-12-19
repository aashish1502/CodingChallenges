import java.io.File

fun main(args: Array<String>) {

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