package com.example.kmdb.Network

enum class Status{
    RUNNING,
    SUCCESS,
    FAILED
}

//class handles network exceptions
class CurrentNetworkState(val status:  Status , val msg: String) {

    companion object {
        val LOADED: CurrentNetworkState
        val LOADING: CurrentNetworkState
        val ERROR: CurrentNetworkState
        val ENDOFRESULT: CurrentNetworkState

        init {
            LOADED = CurrentNetworkState(Status.SUCCESS, "Success")
            LOADING = CurrentNetworkState(Status.RUNNING, "Running")
            ERROR = CurrentNetworkState(Status.FAILED, "Error, when loading")
            ENDOFRESULT = CurrentNetworkState(Status.FAILED, "End of Results")
        }
    }
}
