import React, { useState } from 'react';
import {Formik, Form} from 'formik';
import { TextField } from '../../Components/TextField';
import BannerImage from '../../Components/Assets/logIn.jpg';
import { useNavigate } from 'react-router-dom';
import * as Yup from 'yup';
import { emailBlacklist, passwordBlacklist } from '../../Components/data/Blacklist';
import { Captcha } from '../../Components/Captcha.jsx';
import axiosHttp from '../../Components/utils/axiosHttp.jsx';
import {ToastContainer, toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function RegisterPage() {
    const navigate = useNavigate();
    const randomString = Math.random().toString(36).slice(8);
    const [captcha, setCaptcha] = useState(randomString);
    const refreshString = () => {
      setCaptcha(Math.random().toString(36).slice(8));
    }
    const validate = Yup.object({
      firstName: Yup.string()
          .min(3, "Too short first name")
          .max(15, "Must be 15 characters or less")
          .required("First name is required"),
      lastName: Yup.string()
          .max(20, "Must be 20 characters or less")
          .required("Last name is required"),
      email: Yup.string()
          .email("Email is invalid")
          .notOneOf(emailBlacklist, "Email is in the black list")
          .lowercase()
          .required("Email is required"),
      password: Yup.string()
          .matches(/(?=.*[a-z])/, "One lowercase required")
          .matches(/(?=.*[A-Z])/, "One uppercase required")
          .matches(/(?=.*[0-9])/, "One number required")
          .min(6, "Password must be at least 6 characters")
          .notOneOf(passwordBlacklist, "Password is in the black list")
          .required("Password is required"),
      confirmPassword: Yup.string()
          .oneOf([Yup.ref("password"), null], "Password")
          .required("Confirm password is required"),
      captcha: Yup.string()
          .oneOf([captcha], "You are not a human")
          .required('Captcha is required')
  })
  return (
    <div className='d-flex justify-content-center align-items-center'>
      <ToastContainer position='top-center'/>
        <div className='col-md-5'>
            <Formik
              initialValues={{
                firstName: '',
                lastName: '',
                email: '',
                password: '',
                confirmPassword: '',
                captcha: ''
              }}
              validationSchema={validate}
              onSubmit={async(values) => {
                console.log(values);
                const {firstName, lastName, email, password} = values;
                
                try {
                  await axiosHttp.post("http://localhost:8080/api/register",{
                  ime: firstName,
                  prezime: lastName,
                  email: email,
                  password: password,
                  });
                  toast.success("Korisnik je uspesno ragistrovan");
                  setTimeout(() => {
                      navigate('/logIn');
                  }, 1000);
                  
                      
              } catch (err) {
                  toast.error("Niste uneli ispravne podatke pokusajte ponovo");
              }
              }}
            >
                {formik => (
                     <div>
                     <h1 className='my-4 font-weight-bold-display-4'>Register</h1>
                     <Form>
                        <TextField label="First Name" name="firstName" type="text"/>
                        <TextField label="Last Name" name="lastName" type="text"/>
                        <TextField label="Email" name="email" type="email"/>
                        <TextField label="Password" name="password" type="password"/>
                        <TextField label="Confirm password" name="confirmPassword" type="password"/>
                        <Captcha captcha={captcha} refreshString={refreshString}/>
                        <button className="btn btn-warning mt-3" type="submit">Register</button>
                     </Form>
                 </div>
                )}
            </Formik>
        </div>
        <div className="col-md-5 my-auto">
          <img className="img-fluid w-100" src={BannerImage} alt=""/>
        </div>
    </div>
  )
}

export default RegisterPage