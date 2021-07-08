package com.lucasesteves.beerloversapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lucasesteves.beerloversapp.databinding.FragmentContaBinding



class ContaFragment : Fragment() {
    private var binding: FragmentContaBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this.requireActivity(), gso)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentContaBinding.inflate(inflater, container, false)
        return binding?.root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun signOut() {
        // [START auth_sign_out]
        Firebase.auth.signOut()
        startActivity(Intent(activity, LoginActivity::class.java))
        // [END auth_sign_out]
    }

    private fun updateUI(user: FirebaseUser?) {
        // No-op
    }


}