    //alert(document.getElementById("logoImage").src);
    //var imgPath = document.getElementById("logoImage").src;

        const particles = {
            "fpsLimit": 100,
            "particles": {
            "number": {
                "value": 1,
                "density": {
                "enable": true,
                "value_area": 80
                }
            },
            "color": {
                "value": "#070707",
                "animation": {
                "enable": true,
                "speed": 20,
                "sync": true
                }
            },
            "shape": {
                "type": "image",
                "stroke": {
                "width": 0
                },
                "polygon": {
                "nb_sides": 5
                },
                "images":[
                    {
                        "src": imgSrc, //imageSrc
                        "width": 32,
                        "height": 32

                    },
                ],
            },
            "opacity": {
                "value": 1,
                "random": false,
                "anim": {
                "enable": false,
                "speed": 3,
                "opacity_min": 0.1,
                "sync": false
                }
            },
            "size": {
                "value": 35,
                "random": false,
                "anim": {
                "enable": false,
                "speed": 20,
                "size_min": 0.1,
                "sync": false
                }
            },
            "links": {
                "enable": true,
                "distance": 250,
                "color": "#d40e0e",
                "opacity": 1,
                "width": 4.7
            },
            "move": {
                "enable": true,
                "speed": 3,
                "direction": "right",
                "random": false,
                "straight": false,
                "out_mode": "out",
                "attract": {
                "enable": false,
                "rotateX": 600,
                "rotateY": 1200
                }
            }
            },
            "interactivity": {
            "detect_on": "canvas",
            "events": {
                "onhover": {
                "enable": false,
                "mode": "repulse"
                },
                "onclick": {
                "enable": false,
                "mode": "push"
                },
                "resize": true
            },
            "modes": {
                "grab": {
                "distance": 400,
                "line_linked": {
                    "opacity": 1
                }
                },
                "bubble": {
                "distance": 400,
                "size": 40,
                "duration": 2,
                "opacity": 0.8
                },
                "repulse": {
                "distance": 200
                },
                "push": {
                "particles_nb": 4
                },
                "remove": {
                "particles_nb": 2
                }
            }
            },
            "retina_detect": true,
            "background": {
            "color": "#fff",
            "image": "",
            "position": "50% 50%",
            "repeat": "no-repeat",
            "size": "cover"
            }
        };

//particles.shape.images[0].src = imgSrc;

