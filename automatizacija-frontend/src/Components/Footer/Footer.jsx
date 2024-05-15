import React from 'react';
import { FaInstagram } from 'react-icons/fa';
import { FaTwitter } from 'react-icons/fa';
import { FaFacebook } from 'react-icons/fa';
import { FaLinkedin } from 'react-icons/fa';
import './Footer.css'

export const Footer = () => {
  return (
    <div className='footer'>
      <div className='socialMedia'> 
          <FaInstagram/>
          <FaTwitter/>
          <FaFacebook/>
          <FaLinkedin/>
      </div>
      <p> &copy; 2024 proizvodnjaSokova.com</p>
  </div>
  )
}