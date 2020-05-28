import it.skrape.core.fetcher.Mode
import it.skrape.core.htmlDocument
import it.skrape.extract
import it.skrape.selects.html5.span
import it.skrape.skrape

fun main() {
    scrapeGithub()
}

fun scrapeGithub() {
    val githubUserData = skrape {
        url = "https://github.com/skrapeit"
        mode = Mode.SOURCE

        extract {
            MyScrapedData(
                    userName = htmlDocument {
                        span {
                            withClass = "p-nickname"
                            findFirst {
                                text
                            }
                        }
                    }, // get single user github nickname with tag <span> and class p-nickname
                    repositoryNames = htmlDocument {
                        span {
                            withClass = "repo"
                            findAll {
                                map {
                                    it.text
                                }
                            }
                        }
                    } // get user github repository name with tag <span> and class repo
            )
        }
    }
    println("${githubUserData.userName}'s repos are ${githubUserData.repositoryNames[0]}")
    // skrapeit's repos are skrape.it
}