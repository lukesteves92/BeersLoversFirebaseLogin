package com.lucasesteves.beerloversapp
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.common.SignInButton
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.lucasesteves.beerloversapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class loginFragment : Fragment() {
    private var binding: FragmentLoginBinding? = null
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
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding?.floatingActionButton?.setOnClickListener{
//            binding?.animationView?.isVisible = true
//            Handler().postDelayed({
//                                  }, 2000L)
            val email = binding?.loginEmail?.text.toString()
            val password = binding?.loginSenha?.text.toString()

            signIn(email, password)


        }

        binding?.signInButton?.setOnClickListener{

         signInGoogle()


        }

        binding?.regitrar?.setOnClickListener{

            findNavController().navigate(R.id.action_loginFragment_to_cadastrarFragment)
        }





//        val campo = binding?.campoData
//        val cal = Calendar.getInstance()
//        val savedYear = cal.get(Calendar.YEAR)
//        val savedMonth = cal.get(Calendar.MONTH)
//        val savedDay = cal.get(Calendar.DAY_OF_MONTH)
//        campo?.keyListener = null

//        binding?.campoData?.setOnClickListener {
//            val datePickerDialog = DatePickerDialog(
//                requireActivity(),
//                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
//                    campo?.setText(
//                        "$dayOfMonth/${month + 1}/$year"
//                    )
//                },
//                savedYear,
//                savedMonth,
//                savedDay
//            )
//            datePickerDialog.show()
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun signIn(mail: String, pass: String){
        val email = mail
        val password = pass
          auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    Toast.makeText(context, "Login Efetuado", Toast.LENGTH_LONG).show()
                    startActivity(Intent(activity, MainActivity::class.java))
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(context, "Erro ao efetuar o login, verificar login/senha", Toast.LENGTH_LONG).show()
                    updateUI(null)
                }
            }

    }

    private fun updateUI(user: FirebaseUser?) {

    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    private fun reload() {

    }

    companion object {
        private const val TAG = "EmailPassword"
        private const val TAG2 = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG2, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w(TAG2, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(CadastrarFragment.TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)

                } else {
                    Log.w(CadastrarFragment.TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(context, "Erro ao efetuar o login, tente novamente mais tarde", Toast.LENGTH_LONG).show()
                    updateUI(null)
                }
            }
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    private fun signOut() {
        Firebase.auth.signOut()

    }
}