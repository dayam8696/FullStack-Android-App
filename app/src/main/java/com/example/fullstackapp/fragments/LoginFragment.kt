//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.Fragment
//import com.example.fullstackapp.R
//import com.example.fullstackapp.databinding.LoginFragmentBinding
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.google.android.gms.auth.api.signin.GoogleSignInClient
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.android.gms.common.api.ApiException
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.google.firebase.auth.GoogleAuthProvider
//
//class LoginFragment : Fragment() {
//    private val binding by lazy { LoginFragmentBinding.inflate(layoutInflater) }
//    //private lateinit var googleSignInClient: GoogleSignInClient
//    private lateinit var firebaseAuth: FirebaseAuth
//
//    companion object {
//        private const val RC_SIGN_IN = 9001
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Initialize Firebase Auth
//        firebaseAuth = FirebaseAuth.getInstance()
//
//        // Configure Google Sign In
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id)) // Use your actual client ID
//            .requestEmail()
//            .build()
//
//       // googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
//
//        // Set up the click listener for the Google sign-in button
//        binding.loginWithGoogle.setOnClickListener {
//            signIn()
//        }
//    }
//
//    private fun signIn() {
//       // val signInIntent = googleSignInClient.signInIntent
//        startActivityForResult(signInIntent, RC_SIGN_IN)
//        binding.progressBar2.visibility = View.VISIBLE
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
////        if (requestCode == RC_SIGN_IN) {
////          //  val task = GoogleSignIn.getSignedInAccountFromIntent(data)
////            try {
////            //    val account = task.getResult(ApiException::class.java)
////                firebaseAuthWithGoogle(account)
////            } catch (e: ApiException) {
////                Toast.makeText(requireContext(), "Google sign-in failed", Toast.LENGTH_SHORT).show()
////                binding.progressBar2.visibility = View.GONE
////            }
////        }
////    }
//
//    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
//        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
//        firebaseAuth.signInWithCredential(credential)
//            .addOnCompleteListener(requireActivity()) { task ->
//                if (task.isSuccessful) {
//                    val user = firebaseAuth.currentUser
//                    updateUI(user)
//                } else {
//                    Toast.makeText(requireContext(), "Authentication Failed.", Toast.LENGTH_SHORT).show()
//                    updateUI(null)
//                }
//                binding.progressBar2.visibility = View.GONE
//            }
//    }
//
//    private fun updateUI(user: FirebaseUser?) {
//        if (user != null) {
//            // Navigate to the next screen or update the UI
//            Toast.makeText(requireContext(), "Welcome ${user.displayName}", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(requireContext(), "Please sign in to continue", Toast.LENGTH_SHORT).show()
//        }
//    }
//}
