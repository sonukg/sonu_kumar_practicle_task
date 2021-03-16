package com.sonukg.sonu_kumar_practicle_task

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sonukg.sonu_kumar_practicle_task.adapter.PhotosAdapter
import com.sonukg.sonu_kumar_practicle_task.model.PhotosModel
import com.sonukg.sonu_kumar_practicle_task.repository.PhotosRepository
import com.sonukg.sonu_kumar_practicle_task.serviceapi.ServiceSingleton
import com.sonukg.sonu_kumar_practicle_task.view.PhotosDetailsActivity
import com.sonukg.sonu_kumar_practicle_task.viewmodel.MainViewModel
import com.sonukg.sonu_kumar_practicle_task.viewmodel.factory.MainViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter:PhotosAdapter
    private var progressBar:ProgressBar?=null
    private var recyclerView:RecyclerView?=null
    private var errorMsg:TextView?=null
    private var subscribe: Disposable? = null

    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar=findViewById(R.id.progressBar)
        recyclerView=findViewById(R.id.recyclerView)
        errorMsg=findViewById(R.id.errorMsg)
        onlyShowProgressBar()
        val repository=PhotosRepository(ServiceSingleton.api)
        val mainvmFactory=MainViewModelFactory(repository)
        viewModel=ViewModelProviders.of(this,mainvmFactory).get(MainViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        compositeDisposable += viewModel.getAllPhotosData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { userDataList ->
                    initRecyclerView(userDataList)
                    onlyShowRecyclerView()
                },
                onError = { e -> onlyShowErrorMsg(e.message!!) }
            )


    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.dispose()
    }

    private fun onlyShowProgressBar() {
        progressBar?.visibility = View.VISIBLE
        recyclerView?.visibility = View.INVISIBLE
        errorMsg?.visibility = View.INVISIBLE
    }


    private fun onlyShowRecyclerView() {
        progressBar?.visibility = View.INVISIBLE
        recyclerView?.visibility = View.VISIBLE
        errorMsg?.visibility = View.INVISIBLE
    }


    private fun onlyShowErrorMsg(msg: String) {
        progressBar?.visibility = View.INVISIBLE
        recyclerView?.visibility = View.INVISIBLE
        errorMsg?.visibility = View.VISIBLE
        errorMsg?.text = "Network Error: $msg"
    }

    private fun initRecyclerView(photosDataList: List<PhotosModel>) {

            recyclerView?.setHasFixedSize(true)
            recyclerView?.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PhotosAdapter(photosDataList)
            subscribe= this.adapter.clickEvent.subscribe({
                Toast.makeText(this, "Clicked on $it", Toast.LENGTH_LONG).show()
                val intent=Intent(this,PhotosDetailsActivity::class.java)
                intent.putExtra("url",photosDataList.get(0).url)
                startActivity(intent)
            })
            recyclerView?.adapter=adapter
    }

}