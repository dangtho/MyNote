package com.dangtho.mynote.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dangtho.mynote.R
import com.dangtho.mynote.data.model.ForeCast
import com.dangtho.mynote.data.model.WeatherCurrent
import com.dangtho.mynote.data.model.base.Status
import com.dangtho.mynote.databinding.FragmentMainBinding
import com.dangtho.mynote.debug.ListUrlActivity
import com.dangtho.mynote.view.adapter.ForeCastHourDayAdapter
import com.dangtho.mynote.view.adapter.ForeCastThreedayAdapter
import com.dangtho.mynote.view.base.BaseFragment
import com.dangtho.mynote.view.viewmodel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentViewModel>(), View.OnClickListener {
    private var _binding: FragmentMainBinding? = null
    private lateinit var binding: FragmentMainBinding
    private var foreCastThreedayAdapter: ForeCastThreedayAdapter? = null
    private var foreCastHourDayAdapter: ForeCastHourDayAdapter? = null
    private val mViewModel: HomeFragmentViewModel by viewModels()

    override fun setContext() {
        mContext = binding.root.context
    }

    override fun setViewModel() {
        this.viewModel = mViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutProgressBar.visibility = View.VISIBLE
        binding.myToolBar.setOnLongClickListener {
            val intent = Intent(mContext, ListUrlActivity::class.java)
            activity?.startActivity(intent)
            return@setOnLongClickListener false
        }
        setBackground()
        setForeCastThreeday()
        setUpForeCastHours()
        binding.tvCondition.setOnClickListener(this)

        viewModel?.weatherCurrentDay?.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result?.data?.current?.let { weatherCurrent ->
                        binding.tvCondition.text = weatherCurrent.condition?.text
                        binding.tvTemp.text = mContext?.let { it1 ->
                            String.format(
                                it1.getString(R.string.tempC),
                                weatherCurrent.tempCCurrent
                            )
                        }
                        Glide.with(binding.root.context)
                            .load("https:${weatherCurrent.condition?.icon}")
                            .placeholder(R.drawable.icon_plus)
                            .error(R.drawable.icon_dot_menu)
                            .centerCrop()
                            .into(binding.imgCondition)
                    }
                    binding.myToolBar.setTitleToolBar(result.data?.location?.nameLocation)

                    binding.layoutProgressBar.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.layoutProgressBar.visibility = View.GONE
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    binding.layoutProgressBar.visibility = View.VISIBLE
                }
            }
        }



        viewModel?.weatherForeCast?.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result?.data?.let {
                        // set data for astro
                        setDataAstro(it.forecast?.forecastDay?.get(0), it.current)

                        it.forecast?.forecastDay?.let { weatherForeCast ->
                            // change list for threeday
                            foreCastThreedayAdapter?.setListDay(weatherForeCast)
                            // change list for hours forecast
                            weatherForeCast[0].foreCastHour?.let { hourForeCast ->
                                foreCastHourDayAdapter?.setListDay(
                                    hourForeCast,
                                    weatherForeCast
                                )
                            }
                        }
                    }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setBackground() {
//        if (DateUtil.getStateDay() == StateDay.NIGHT) {
//            binding.rootView.background =
//                mContext?.let { ContextCompat.getDrawable(it, R.drawable.bground_night) }
//        } else if (DateUtil.getStateDay() == StateDay.NOON) {
//            binding.rootView.background =
//                mContext?.let { ContextCompat.getDrawable(it, R.drawable.bground_morning) }
//        }
    }

    private fun setDataAstro(foreCastDay: ForeCast.ForeCastDay?, current: WeatherCurrent?) {
        binding.itemAstro.tvFeelikeTempc.text = foreCastDay?.astro?.moonIllumination.toString()
        binding.itemAstro.tvSunriseNumber.text = foreCastDay?.astro?.sunrise
        binding.itemAstro.tvMoonriseNumber.text = foreCastDay?.astro?.moonrise
        binding.itemAstro.tvSunSetNumber.text = foreCastDay?.astro?.sunset
        binding.itemAstro.tvMoonSetNumber.text = foreCastDay?.astro?.moonset
        binding.itemAstro.tvMaybeRainNumber.text = foreCastDay?.day?.mayBeRain
        // set data current
        binding.itemAstro.tvHumidityNumber.text = current?.humidity
        binding.itemAstro.tvWindSpeedNumber.text = current?.windKph
        binding.itemAstro.tvUvNumber.text = current?.uv

    }

    private fun setUpForeCastHours() {
        val layoutManager = LinearLayoutManager(mContext)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rcvForecastHourDay.layoutManager = layoutManager
        if (foreCastHourDayAdapter == null) {
            foreCastHourDayAdapter = ForeCastHourDayAdapter()
        }
        binding.rcvForecastHourDay.adapter = foreCastHourDayAdapter
    }

    fun setForeCastThreeday() {
        binding.rcvForecastThreeDay.layoutManager = LinearLayoutManager(mContext)
        if (foreCastThreedayAdapter == null) {
            foreCastThreedayAdapter = ForeCastThreedayAdapter()
        }
        binding.rcvForecastThreeDay.adapter = foreCastThreedayAdapter
    }

    override fun onClick(view: View?) {

    }
}

