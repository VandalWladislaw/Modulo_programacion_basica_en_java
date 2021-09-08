package com.wandal.wave.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wandal.wave.*
import com.wandal.wave.fragments.FragmentPlaying
import com.wandal.wave.fragments.FragmentPlaylist
import com.wandal.wave.fragments.FragmentTrack


class ViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
    companion object{
        private const val ARG_OBJECT = "object"
    }

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{return FragmentPlaying()
            }
            1->{return FragmentPlaylist()
            }
            2->{return FragmentAlbum()
            }
            3->{return FragmentTrack()
            }
            4->{return FragmentFolder()
            }
            else -> FragmentPlaying()
        }
    }
}