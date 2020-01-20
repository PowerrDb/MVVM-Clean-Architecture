package com.challenge.presentation.ui.detainFragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.challenge.domain.common.ResultState
import com.challenge.domain.entity.Entity
import com.challenge.presentation.R
import com.challenge.presentation.common.FastBlurUtil
import com.challenge.presentation.common.extention.observe
import com.challenge.presentation.databinding.FragmentDetailBinding
import com.challenge.presentation.ui.base.BaseFragment
import com.challenge.presentation.ui.listCharacterFragment.ListCharacterViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject


class DetailPageFragment : BaseFragment() {
    private lateinit var binding: FragmentDetailBinding
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel: ListCharacterViewModel by lazy {
        activity.let {
            ViewModelProviders.of(it!!, viewModelFactory).get(ListCharacterViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater,
            R.layout.fragment_detail,
            container,
            false
        )


        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPic(imageView5)
        Glide.with(this).asBitmap().circleCrop().load(R.drawable.templar_assassin).into(imageView6)
        observe(mainViewModel.characterInfoLiveData,::onGetCharacterInfo)
        mainViewModel.getCharacterInfo(arguments!!.getString("id",""))
    }

    private fun onGetCharacterInfo(resultState: ResultState<Entity.Character>) {
        when (resultState) {
            is ResultState.Loading ->{Log.e("__infoloading",resultState.data.toString())}
            is ResultState.Success ->{Log.e("__infoSucces",resultState.data.toString())}
            is ResultState.Error ->{Log.e("__infoError",resultState.data.toString())}
        }
    }

    fun loadPic( backgroundView: View) {
        Glide.with(this).asBitmap().load(R.drawable.templar_assassin).into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                val wm = activity!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val display = wm.defaultDisplay
                val size = Point()
                display.getSize(size)
                val width = size.x
                val height = activity!!.resources!!.getDimension(R.dimen._170sdp).toInt()
                val outputBitmap = Bitmap.createScaledBitmap(resource, width, height, false)
                val bitmap = FastBlurUtil.doBlur(outputBitmap, 100, true)
                var drawable = BitmapDrawable(resources, bitmap);

                backgroundView.background = drawable
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })


    }
}






