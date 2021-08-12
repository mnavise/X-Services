package com.kolot.x_servicesbeta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kolot.x_servicesbeta.databinding.ActivityPengaturanBinding
import com.kolot.x_servicesbeta.databinding.ActivityPengaturanUserBinding

class PengaturanUserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityPengaturanUserBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengaturanUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        binding.btnSignOut.setOnClickListener(this)
        binding.btnInfo.setOnClickListener(this)
        binding.btnEmailVerify.setOnClickListener(this)
        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this@PengaturanUserActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            updateUI(currentUser)
        }
    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this@PengaturanUserActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnSignOut -> {
                signOut()
            }
            R.id.btnEmailVerify -> {
                sendEmailVerification()
            }
            R.id.btnInfo -> {
                val intent = Intent(this@PengaturanUserActivity, DataActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun sendEmailVerification() {
        binding.btnEmailVerify.isEnabled = false
        val user = auth.currentUser!!
        user.sendEmailVerification()
                .addOnCompleteListener(this) { task ->
                    binding.btnEmailVerify.isEnabled = true
                    if (task.isSuccessful) {
                        Toast.makeText(baseContext,
                                "Verification email sent to ${user.email} ",
                                Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(baseContext,
                                "Failed to send verification email.",
                                Toast.LENGTH_SHORT).show()
                    }
                }
    }
    private fun signOut() {
        auth.signOut()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this@PengaturanUserActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        googleSignInClient.signOut().addOnCompleteListener(this) {
        }
    }
    private fun updateUI(currentUser: FirebaseUser) {
        currentUser?.let {
            binding.btnEmailVerify.isVisible = false
            val name = currentUser.displayName
            val phoneNumber = currentUser.phoneNumber
            val email = currentUser.email
            val photoUrl = currentUser.photoUrl
            val emailVerified = currentUser.isEmailVerified
            val uid = currentUser.uid

            Glide.with(this)
                    .load(photoUrl)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.mipmap.ic_launcher)
                            .centerCrop()
                            .circleCrop())
                    .into(binding.ivImage)

            binding.tvName.text = name
            if(TextUtils.isEmpty(name)){
                binding.tvName.text = "No Name"
            }
            binding.tvUserId.text = email
            for (profile in it.providerData) {
                val providerId = profile.providerId
                if(providerId=="password" && emailVerified==false){
                    binding.btnEmailVerify.isVisible = true
                }
                if(providerId=="phone"){
                    binding.tvName.text = phoneNumber
                    binding.tvUserId.text = providerId
                }
            }
        }
    }
}