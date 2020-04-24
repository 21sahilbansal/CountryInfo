package com.example.countryinfo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.countryinfo.R
import retrofit2.HttpException
import java.io.IOException

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(getLayoutRes(), container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewInitialization(view)
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    protected fun handleError(th: Throwable) {
        val error = when (th) {
            is HttpException -> when (th.code()) {
                401 -> getString(R.string.error_unauthorized)
                500 -> getString(R.string.error_server)
                else -> getString(R.string.error_server)
            }
            is IOException -> getString(R.string.internet_error)
            else -> th.message.toString()
        }
        onError(error)
    }

    abstract fun viewInitialization(view: View)
    abstract fun getLayoutRes(): Int
    abstract fun showLoadingState(loading: Boolean)
    abstract fun onError(message: String)
}