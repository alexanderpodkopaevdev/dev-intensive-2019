package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16) : String {
    return if (this.trimEnd().length > value) {
         "${this.substring(0, value).trimEnd()}..."
    } else this.trimEnd()
}

fun String.stripHtml() : String {
    var newString = (this.substring(this.indexOf('>',0)+1,this.indexOf('<',this.indexOf('>',0)))).replace(Regex("\\s+"), " ")
    return newString
}