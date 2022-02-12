package hr.ferit.job_seeker.ui.description

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import hr.ferit.job_seeker.R


class PurposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_purpose, container, false)
        view.findViewById<Button>(R.id.previousButtonPurpose).setOnClickListener(){
            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.aboutFragment, InfoFragment())
            fragmentTransaction?.commit()
        }
        view.findViewById<Button>(R.id.nextButtonPurpose).setOnClickListener(){
                activity?.finish()
        }
        return view
    }

}