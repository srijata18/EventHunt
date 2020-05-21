package com.example.eventhunt.dashboard.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.eventhunt.R
import com.example.eventhunt.dashboard.dataModel.FeaturedModel
import com.example.eventhunt.dashboard.dataModel.PopularModel
import com.example.eventhunt.dashboard.viewModel.DashboardViewModel
import com.example.eventhunt.databinding.ActivityMainBinding
import com.example.eventhunt.utils.AppInitializer
import com.example.eventhunt.utils.CustomAlertDialog
import com.example.eventhunt.utils.Thoughts
import com.example.eventhunt.view.base.BaseActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.layout_home.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/*
* Launching activity, which is resonsible for displaying the list of restaurants based on the
* user requirements. Search functionality is also added which can enhance the users need for  a
* particular restaurant.
* */
class DashBoardActivity : BaseActivity<ActivityMainBinding, DashboardViewModel>(),
    ItemSelected {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main

    @Inject
    override lateinit var viewModel: DashboardViewModel
        internal set

    private val response = arrayListOf<FeaturedModel>()
    private var activityMainBinding: ActivityMainBinding? = null
    private var thingsToDoAdapter : ThingsToDoAdapter?=null
    private var thingsTodoList = arrayListOf<String>()
    private var featureAdapter: ScrollAdapter?=null
    private var onlineCourseAdapter : ScrollAdapter?=null
    private var comedyAdapter: ScrollAdapter?=null
    private var musicAdapter: ScrollAdapter?=null
    private var workshopAdapter: ScrollAdapter?=null
    private var talksAdapter: ScrollAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = viewDataBinding
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        setAdapter()
        setLiveListeners()
        loadData()
        setListeners()
        val s = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)
        iv_toolbar_icon?.startAnimation(s)
    }


    private fun setListeners(){
        srl_dashboard?.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                loadData()
            }
        })
    }


    private fun setAdapter(){
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        featureAdapter = ScrollAdapter(arrayListOf<FeaturedModel>(), this, this, Type.Horizontal)
        activityMainBinding?.apply {
            rv_FeaturedEvents?.layoutManager = mLayoutManager
            rv_FeaturedEvents?.adapter = featureAdapter
            rv_FeaturedEvents?.isNestedScrollingEnabled = false
        }
        featureAdapter?.notifyDataSetChanged()

        val mLayoutManager2 = LinearLayoutManager(this)
        mLayoutManager2.orientation = LinearLayoutManager.HORIZONTAL
        thingsToDoAdapter = ThingsToDoAdapter(thingsTodoList, this, this)
        activityMainBinding?.apply {
            rv_thingsToDo?.layoutManager = mLayoutManager2
            rv_thingsToDo?.adapter = thingsToDoAdapter
            rv_thingsToDo?.isNestedScrollingEnabled = false
        }
        thingsToDoAdapter?.notifyDataSetChanged()

        val mLayoutManager3 = LinearLayoutManager(this)
        mLayoutManager3.orientation = LinearLayoutManager.HORIZONTAL
        onlineCourseAdapter = ScrollAdapter( arrayListOf<FeaturedModel>(), this, this)
        activityMainBinding?.apply {
            rv_onlineCoarse?.layoutManager = mLayoutManager3
            rv_onlineCoarse?.adapter = onlineCourseAdapter
            rv_onlineCoarse?.isNestedScrollingEnabled = false
        }
        onlineCourseAdapter?.notifyDataSetChanged()

        val mLayoutManager4 = LinearLayoutManager(this)
        mLayoutManager4.orientation = LinearLayoutManager.HORIZONTAL
        comedyAdapter = ScrollAdapter( arrayListOf<FeaturedModel>(), this, this)
        activityMainBinding?.apply {
            rv_comedy?.layoutManager = mLayoutManager4
            rv_comedy?.adapter = comedyAdapter
            rv_comedy?.isNestedScrollingEnabled = false
        }
        comedyAdapter?.notifyDataSetChanged()

        val mLayoutManagerMusic = LinearLayoutManager(this)
        mLayoutManagerMusic.orientation = LinearLayoutManager.HORIZONTAL
        musicAdapter = ScrollAdapter( arrayListOf<FeaturedModel>(), this, this)
        activityMainBinding?.apply {
            rv_music?.layoutManager = mLayoutManagerMusic
            rv_music?.adapter = musicAdapter
            rv_music?.isNestedScrollingEnabled = false
        }
        musicAdapter?.notifyDataSetChanged()

        val mLayoutManagerWorkshops = LinearLayoutManager(this)
        mLayoutManagerWorkshops.orientation = LinearLayoutManager.HORIZONTAL
        workshopAdapter = ScrollAdapter( arrayListOf<FeaturedModel>(), this, this)
        activityMainBinding?.apply {
            rv_workshops?.layoutManager = mLayoutManagerWorkshops
            rv_workshops?.adapter = workshopAdapter
            rv_workshops?.isNestedScrollingEnabled = false
        }
        workshopAdapter?.notifyDataSetChanged()

        val mLayoutManagerTalks = LinearLayoutManager(this)
        mLayoutManagerTalks.orientation = LinearLayoutManager.HORIZONTAL
        talksAdapter = ScrollAdapter( arrayListOf<FeaturedModel>(), this, this)
        activityMainBinding?.apply {
            rv_talks?.layoutManager = mLayoutManagerTalks
            rv_talks?.adapter = talksAdapter
            rv_talks?.isNestedScrollingEnabled = false
        }
        talksAdapter?.notifyDataSetChanged()
    }

    private fun loadData(){
        if (checkForInternet()) {
            hideEmptyScreen()
            showHideProgress(true)
            viewModel.fetchDetails()
        }else{
            Snackbar.make(tv_thingsToDo, getString(R.string.nointernet), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            showHideProgress(false)
            if (response.isEmpty())
                showEmptyScreen()
        }
    }
    private fun setLiveListeners(){
        viewModel.receivedResponse.observe(this, Observer {
            Log.i("res:: Lits Master",it.list?.categorywiseList?.Comedy?.toString())
            val list = arrayListOf<String>()
            it?.groups?.let { it1 -> list.addAll(it1) }
            thingsTodoList = list
            thingsToDoAdapter?.updateAdapter(thingsTodoList)
            thingsToDoAdapter?.notifyDataSetChanged()

            response.apply {
                clear()
                it?.featured?.let { it1 -> this.addAll(it1) }
            }
            val featureList = arrayListOf<FeaturedModel>()
            it?.featured?.let { it1 -> featureList.addAll(it1) }
            featureAdapter?.updateAdapter(featureList)

            var responseArray = featureList?.filter { it?.category_id?.name?.equals("Comedy") }.toTypedArray()
            val responseArrayList = arrayListOf<FeaturedModel>()
            responseArrayList?.apply {
                clear()
                addAll(responseArray)
            }
            comedyAdapter?.updateAdapter(responseArrayList)

            responseArray = featureList?.filter { it?.category_id?.name?.equals("Music") }.toTypedArray()
            responseArrayList?.apply {
                clear()
                addAll(responseArray)
            }
            musicAdapter?.updateAdapter(responseArrayList)

            responseArray = featureList?.filter { it?.category_id?.name?.equals("Online Course") }.toTypedArray()
            responseArrayList?.apply {
                clear()
                addAll(responseArray)
            }
            onlineCourseAdapter?.updateAdapter(responseArrayList)

            responseArray = featureList?.filter { it?.category_id?.name?.equals("Workshops") }.toTypedArray()
            responseArrayList?.apply {
                clear()
                addAll(responseArray)
            }
            workshopAdapter?.updateAdapter(responseArrayList)

            responseArray = featureList?.filter { it?.category_id?.name?.equals("Talks") }.toTypedArray()
            responseArrayList?.apply {
                clear()
                addAll(responseArray)
            }
            talksAdapter?.updateAdapter(responseArrayList)
            hideShowAllViews(View.VISIBLE)
            showHideProgress(false)

        })
        viewModel.errorModel.observe(this, Observer {
            it?.message?.let { it1 -> CustomAlertDialog.displayAlert(this, it1,getString(R.string.ok)) }
            showEmptyScreen()
            showHideProgress(false)
        })
    }
    private fun showHideProgress(toShow : Boolean){
        if(toShow){
            if (srl_dashboard?.isRefreshing==false) {
                hideShowAllViews(View.GONE)
                quicky_msg?.text = Thoughts.displayThoughts()
                layout_progress?.visibility = View.VISIBLE
            }
        }else{
            layout_progress?.visibility = View.GONE
            srl_dashboard?.isRefreshing = false
        }
    }

    private fun hideShowAllViews(value : Int){
        /*layout_emptystate?.visibility = value
        tv_thingsToDo?.visibility = value
        rv_thingsToDo?.visibility = value
        tv_FeaturedEvents?.visibility = value
        rv_FeaturedEvents?.visibility = value
        tv_comedy?.visibility = value
        rv_comedy?.visibility = value
        tv_music?.visibility = value
        rv_music?.visibility = value
        tv_workshops?.visibility = value
        rv_workshops?.visibility = value
        tv_onlineCoarse?.visibility = value
        rv_onlineCoarse?.visibility = value
        tv_talks?.visibility = value
        rv_talks?.visibility = value        */
        main_container?.visibility = value
    }

    override fun onItemSelected(variantName : String?) {

    }

    private fun showEmptyScreen(){
        layout_emptystate?.visibility = View.VISIBLE
    }

    private fun hideEmptyScreen(){
        layout_emptystate?.visibility = View.GONE
    }

    private fun showNoData() {
        quicky_msg?.visibility = View.VISIBLE
        quicky_msg?.text = getString(R.string.emptyList)
    }
}


