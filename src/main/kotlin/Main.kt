import kotlin.system.exitProcess
var lotSize = 0
var createCount = 0

fun main(args: Array<String>) {

    var parkingLot: MutableList<MutableList<String>> = mutableListOf()
    initializeParkingLot(parkingLot)


    loop@ while (true) {
        var userInput = readln().split(" ").toMutableList()
        if (userInput[0] == "exit") {
            exit()
        } else if (userInput[0] == "create") {
            createParkingLot(parkingLot, userInput)
            continue@loop
        } else if (lotSize == 0 && userInput[0] != "create" ) {
            println("Sorry, a parking lot has not been created.")
            continue@loop
        } else if (userInput[0] == "status") {
            status(parkingLot, userInput)
            continue@loop
        } else if (userInput[0] == "reg_by_color"){
            regByColor(parkingLot, userInput)
            continue@loop
        } else if (userInput[0] == "spot_by_color"){
            spotByColor(parkingLot, userInput)
            continue@loop
        } else if (userInput[0] == "spot_by_reg"){
            spotByReg(parkingLot, userInput)
            continue@loop
        }
        else
            park(userInput, parkingLot)
        leave(userInput, parkingLot)
    }
}

fun initializeParkingLot (parkingLot: MutableList<MutableList<String>>) {
    for (r in 0 until lotSize) {
        parkingLot.add(mutableListOf("open", "open", "open"))
    }
}

fun park (userInput: MutableList<String>, parkingLot: MutableList<MutableList<String>>) {
    var emptySpace = emptySpace(parkingLot)
    if (userInput[0] == "park") {
        if (emptySpace > 0) {
            recordParkedCar(userInput, parkingLot, emptySpace)
            println("${userInput[2]} car parked in spot ${emptySpace}.")
        }
        else {
            println("Sorry, the parking lot is full.")
        }
    }
}

fun emptySpace (parkingLot: MutableList<MutableList<String>>): Int { //checks if there is an empty space
    var firstEmptySpace: Int = 0
    for (r in 0 until lotSize) {
        if (parkingLot[r][0] == "open") {
            firstEmptySpace = r + 1
            break
        } else firstEmptySpace = -1
    }
    return firstEmptySpace
}

fun leave (userInput: MutableList<String>, parkingLot: MutableList<MutableList<String>>) {
    if (userInput[0] == "leave") {
        println("Spot ${userInput[1]} is free.")
        parkingLot[userInput[1].toInt()-1].fill("open")
    }
}

fun recordParkedCar (userInput: MutableList<String>, parkingLot: MutableList<MutableList<String>>, firstEmptySpace: Int) {
    parkingLot[firstEmptySpace-1][0] = "filled"
    parkingLot[firstEmptySpace-1][1] = userInput[1].toString()
    parkingLot[firstEmptySpace-1][2] = userInput[2].toString()
}

fun exit () {
    exitProcess(0)
}

fun status (parkingLot: MutableList<MutableList<String>>, userInput: MutableList<String>) {
    if (numberOfParkedCars(parkingLot) == 0 && lotSize != 0) {
        println("Parking lot is empty.")
    }
    else if (lotSize == 0) {
        println("Sorry, a parking lot has not been created.")
    }
    else for (r in 0 until lotSize) {
        if (parkingLot[r][0] != "open") {
            println("${r+1} ${parkingLot[r][1]} ${parkingLot[r][2]}")
        }
    }
}

fun numberOfParkedCars (parkingLot: MutableList<MutableList<String>>): Int {
    var parkedCars: Int = 0
    for (r in 0 until lotSize) {
        if (parkingLot[r][0] != "open") {
            parkedCars++
        }
    }
    return parkedCars
}

fun createParkingLot (parkingLot: MutableList<MutableList<String>>, userInput: MutableList<String>) {
    if (userInput[0] == "create" && lotSize != 0) {
        lotSize = userInput[1].toInt()
        initializeParkingLot(parkingLot)
        for (r in 0 until userInput[1].toInt()) {
            parkingLot[r].fill("open")
        }
        println("Created a parking lot with ${userInput[1]} spots.")
        initializeParkingLot(parkingLot)
    } else if (userInput[0] == "create" && userInput[1].toInt() > 0) {
        lotSize = userInput[1].toInt()
        println("Created a parking lot with ${userInput[1]} spots.")
        initializeParkingLot(parkingLot)
    }
}

fun spotByColor (parkingLot: MutableList<MutableList<String>>, userInput: MutableList<String>) {
    var spotByColor = mutableListOf<Int>()
    var a = 0


    for (r in 0 until lotSize) {
        if (parkingLot[r][2].uppercase() == userInput[1].uppercase()) {
            spotByColor.add(r+1)
            a++
        }
    }
    if (numberOfParkedCars(parkingLot) == 0 && lotSize != 0) {
        println("Parking lot is empty.")
    } else if (lotSize == 0) {
        println("Sorry, a parking lot has not been created.")
    }
    if ((userInput[0] == "spot_by_color") && spotByColor.isEmpty()) {
        println("No cars with color ${userInput[1].uppercase()} were found.")
    } else if (spotByColor.isNotEmpty()) {
        println(spotByColor.joinToString())
        a = 0
    }
}

fun regByColor (parkingLot: MutableList<MutableList<String>>, userInput: MutableList<String>) {
    var regByColor = mutableListOf<String>()
    var a = 0

    for (r in 0 until lotSize) {
        if (parkingLot[r][2].uppercase() == userInput[1].uppercase()) {
            regByColor.add(parkingLot[r][1])
            a++
        }
    }
    if (numberOfParkedCars(parkingLot) == 0 && lotSize != 0) {
        println("Parking lot is empty.")
    } else if (lotSize == 0) {
        println("Sorry, a parking lot has not been created.")
    } else if (userInput[0] == "reg_by_color" && regByColor.isEmpty()) {
        println("No cars with color ${userInput[1].uppercase()} were found.")
    } else if (regByColor.isNotEmpty()) {
        println(regByColor.joinToString())
        a = 0
    }
}

fun spotByReg (parkingLot: MutableList<MutableList<String>>, userInput: MutableList<String>) {
    var spotByReg = mutableListOf<Int>()
    var a = 0

    for (r in 0 until lotSize) {
        if (parkingLot[r][1].uppercase() == userInput[1].uppercase()) {
            spotByReg.add(r+1)
            a++
        }
    }
    if (numberOfParkedCars(parkingLot) == 0 && lotSize != 0) {
        println("Parking lot is empty.")
    }
    else if (userInput[0] == "spot_by_reg" && spotByReg.isEmpty()) {
        println("No cars with registration number ${userInput[1]} were found.")
    } else if (spotByReg.isNotEmpty()) {
        println(spotByReg.joinToString())
        a = 0
    }
}