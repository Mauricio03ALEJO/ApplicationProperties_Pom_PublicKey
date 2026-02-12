<script setup>
import TheWelcome from '../components/TheWelcome.vue'
</script>

<template>
  <main>
    <TheWelcome />
  </main>
</template>

//AuthClient
/*import axios from 'axios';

// Ahora obtenerToken recibe usuario y password como argumentos
export const obtenerToken = async (usuario, password) => {
    const response = await axios.get(`http://localhost:8082/auth/token?usuario=${usuario}&password=${password}`);
    return response.data;
}

// obtenerTokenFacade también acepta usuario y password
export const obtenerTokenFacade = async (usuario, password) => {
    return await obtenerToken(usuario, password);
} */

//MedicoClient
/*import axios from "axios";
const BaseUrl = 'http://localhost:8085/api/medico/v1.0';
const DoctoresUrl = `${BaseUrl}/doctores`;
const PacientesUrl = `${BaseUrl}/pacientes`;
const CitasUrl = `${BaseUrl}/citas`; */
/*// CRUD Doctores
const consultarTodosDoctores = async () => {
    const token = localStorage.getItem('token');
    console.log(token);
    const data = await axios.get(DoctoresUrl, { headers: { Authorization: `Bearer ${token}` } });
    return data.data;
}

const consultarDoctorPorCedula = async (cedula) => {
    const token = localStorage.getItem('token');
    const data = await axios.get(`${DoctoresUrl}/${cedula}`, { headers: { Authorization: `Bearer ${token}` } });
    return data.data;
}

const guardarDoctor = async (body) => {
    const token = localStorage.getItem('token');
    const response = await axios.post(DoctoresUrl, body, { headers: { Authorization: `Bearer ${token}` } });
    return response.data;
}

const actualizarDoctor = async (id, body) => {
    const token = localStorage.getItem('token');
    const response = await axios.put(`${DoctoresUrl}/${id}`, body, { headers: { Authorization: `Bearer ${token}` } });
    return response.data;
}

const actualizarDoctorParcial = async (id, body) => {
    const token = localStorage.getItem('token');
    const response = await axios.patch(`${DoctoresUrl}/${id}`, body, { headers: { Authorization: `Bearer ${token}` } });
    return response.data;
}

const borrarDoctor = async (id) => {
    const token = localStorage.getItem('token');
    await axios.delete(`${DoctoresUrl}/${id}`, { headers: { Authorization: `Bearer ${token}` } });
} */
/*// Fachadas Doctores
export const consultarTodosDoctoresFachada = async () => {
    return await consultarTodosDoctores();
}
export const consultarDoctorPorCedulaFachada = async (cedula) => {
    return await consultarDoctorPorCedula(cedula);
}
export const guardarDoctorFachada = async (body) => {
    return await guardarDoctor(body);
}
export const actualizarDoctorFachada = async (id, body) => {
    return await actualizarDoctor(id, body);
}
export const actualizarDoctorParcialFachada = async (id, body) => {
    return await actualizarDoctorParcial(id, body);
}
export const borrarDoctorFachada = async (id) => {
    await borrarDoctor(id);
} */

//App

<script setup>
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import { provide } from 'vue'
import { obtenerTokenFacade } from './clients/AuthClient'

// Provee getToken a toda la app, ahora acepta usuario y password
provide('getToken', async (usuario, password) => {
  const tokenData = await obtenerTokenFacade(usuario, password)
  return tokenData.accessToken
})
</script>

<template>

  <nav>
    <RouterLink to="/">Home</RouterLink>
    <RouterLink to="/about">About</RouterLink>
    <RouterLink to="/consultarTodos">Consultar Todos</RouterLink>
    <RouterLink to="/consultarPorID">Consultar por ID</RouterLink>
    <RouterLink to="/guardar">Guardar Estudiante</RouterLink>
    <RouterLink to="/actualizar">Actualizar Estudiante</RouterLink>
    <RouterLink to="/actualizarParcial">Actualizar Parcial</RouterLink>
    <RouterLink to="/eliminar">Eliminar Estudiante</RouterLink>
  </nav>

  <RouterView />
</template>

<style scoped>


nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}


</style>

//index
/*import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: () => import('../components/Login.vue'),
    },
    {
      path: '/',
      name: 'home',
      component: HomeView,
      meta: {
        requireAutorizacion: true,
        esPublica: false
      }
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/AboutView.vue'),
      meta: {
        requireAutorizacion: false,
        esPublica: true
      }
    },
    // Rutas para estudiantes (sin /estudiantes)
    {
      path: '/consultarTodos',
      name: 'consultarTodos',
      component: () => import('../views/ConsultarTodos.vue'),
      meta: {
        requireAutorizacion: true,
        esPublica: false
      }
    },
    {
      path: '/consultarPorID',
      name: 'consultarPorID',
      component: () => import('../views/ConsultarPorID.vue'),
      meta: {
        requireAutorizacion: false,
        esPublica: true
      }
    },
    {
      path: '/guardar',
      name: 'guardarEstudiante',
      component: () => import('../views/GuardarEstudiante.vue'),
      meta: {
        requireAutorizacion: true,
        esPublica: false
      }
    },
    {
      path: '/actualizar',
      name: 'actualizarEstudiante',
      component: () => import('../views/ActualizarEstudiante.vue'),
      meta: {
        requireAutorizacion: false,
        esPublica: true
      }
    },
    {
      path: '/actualizarParcial',
      name: 'actualizarParcialEstudiante',
      component: () => import('../views/ActualizarParcialEstudiante.vue'),
      meta: {
        requireAutorizacion: true,
        esPublica: false
      }
    },
    {
      path: '/eliminar',
      name: 'eliminarEstudiante',
      component: () => import('../views/EliminarEstudiante.vue'),
      meta: {
        requireAutorizacion: false,
        esPublica: true
      }
    },
  ],
})

/* Configuracion del Guardian */
router.beforeEach((to, from, next) => {
  if (to.meta.requireAutorizacion){
    /* le envio a una pagina de login */
    const estaAutenticado = localStorage.getItem("estaAutenticado");
    const token = localStorage.getItem("token");
    if(!estaAutenticado){
      console.log("Redirigiendo a login");
      next({name: 'login'});
    }else {
      console.log("Autenticado, puede pasar");
      next();
    }
  }else {
    /* le dejo sin validaciones */
    console.log("Pase libre");
    next();
  }
})

export default router
 */

//LoginView
<template>
  <Login />
</template>

<script>
import Login from '../components/Login.vue'
//export default {
  components: {
    Login
  }
//}
</script>

<style>

</style>

//Login
<template>
    <div class="login">
        <h2>Login</h2>
        <input v-model="usuario" type="text" placeholder="Usuario">
        <input v-model="password" type="password" placeholder="Contraseña">
        <button type="submit" @click="login">Entrar</button>
        <div v-if="error" class="error-message">{{ error }}</div>
    </div>
</template>

<script>
import { obtenerTokenFacade } from '../clients/AuthClient';
export default {
    data(){
        return{
            usuario: '', 
            password: '', 
            error: ''
        };
    },
    methods: {
        async login(){
            this.error = '';
            try {
                // Llama a la API usando los valores del formulario
                const response = await obtenerTokenFacade(this.usuario, this.password);
                const TOKEN = response.accessToken || response.token || null;
                if(TOKEN){
                    localStorage.setItem('token', TOKEN);
                    localStorage.setItem('estaAutenticado', true);
                    this.error = '';
                } else {
                    this.error = 'Usuario o contraseña incorrectos.';
                }
            } catch (error) {
                this.error = 'Usuario o contraseña incorrectos.';
            }
        }
    }
}
</script>

<style>
.login {
    max-width: 300px;
    margin: 50px auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
input{
    width: 100%;
    margin-bottom: 10px;
    padding: 8px;
}
button {
    width: 100%;
    padding: 8px;
    background-color: #42b983;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
 .error-message {
    color: #fff;
    background: #e74c3c;
    padding: 8px;
    border-radius: 4px;
    margin-top: 10px;
    text-align: center;
}
</style>

//ConsultarTodosView
<template>
  <div>
    <h2>Consultar Todos los Estudiantes</h2>
    <button @click="consultarTodos">Consultar</button>
    <table v-if="estudiantes.length" border="1" style="margin-top:1rem;">
      <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Fecha Nacimiento</th>
          <th>Provincia</th>
          <th>Género</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="est in estudiantes" :key="est.id">
          <td>{{ est.id }}</td>
          <td>{{ est.nombre }}</td>
          <td>{{ est.apellido }}</td>
          <td>{{ est.fechaNacimiento }}</td>
          <td>{{ est.provincia }}</td>
          <td>{{ est.genero }}</td>
        </tr>
      </tbody>
    </table>
    <div v-if="error" style="color:red;">{{ error }}</div>
  </div>
</template>

<script>
import { consultarTodosFachada } from '../clients/MatriculaClient';
export default {
  inject: ['getToken'],
  data() {
    return {
      estudiantes: [],
      error: ''
    }
  },
  methods: {
    async consultarTodos() {
      this.error = '';
      try {
        const res = await consultarTodosFachada();
        this.estudiantes = Array.isArray(res) ? res : [];
        if (!this.estudiantes.length) this.error = 'No se encontraron estudiantes.';
      } catch (e) {
        this.error = 'Error al consultar';
        this.estudiantes = [];
      }
    }
  }
}
</script>

//AboutComponent
/*
<template>
	<div>
		<button @click="consultarTodos">Consultar Todos</button>
		<button @click="consultarPorId">Consultar por ID</button>
		<button @click="guardar">Guardar</button>
		<button @click="actualizar">Actualizar</button>
		<button @click="actualizarParcial">Actualizar Parcial</button>
		<button @click="borrar">Borrar</button>
		<div style="margin-top: 1em;">
			<pre>{{ resultado }}</pre>
		</div>
	</div>
</template>

<script>
import { consultarTodosFachada, consultarPorIdFachada, guardarFachada, actualizarFachada, actualizarParcialFachada, borrarFachada } from '../clients/MatriculaClient';

export default {
	data() {
		return {
			resultado: '',
		};
	},
	methods: {
		async consultarTodos() {
			const res = await consultarTodosFachada();
			console.log('Consultar Todos:', res);
			this.resultado = res;
		},
		async consultarPorId() {
			const id = prompt('Ingrese el ID a consultar:');
			if (id) {
				try {
					const res = await consultarPorIdFachada(id);
					console.log('Consultar por ID:', res);
					this.resultado = res;
				} catch (e) {
					console.error('Error al consultar por ID:', e);
					this.resultado = 'Error al consultar por ID';
				}
			}
		},
		async guardar() {
			const nombre = prompt('Nombre:');
			const apellido = prompt('Apellido:');
			if (nombre && apellido) {
				const body = { nombre, apellido };
				this.resultado = await guardarFachada(body);
			}
		},
		async actualizar() {
			const id = prompt('ID a actualizar:');
			const nombre = prompt('Nuevo nombre:');
			const apellido = prompt('Nuevo apellido:');
			if (id && nombre && apellido) {
				const body = { nombre, apellido };
				this.resultado = await actualizarFachada(id, body);
			}
		},
		async actualizarParcial() {
			const id = prompt('ID a actualizar parcialmente:');
			const campo = prompt('Campo a actualizar (nombre o apellido):');
			const valor = prompt('Nuevo valor:');
			if (id && campo && valor) {
				const body = { [campo]: valor };
				this.resultado = await actualizarParcialFachada(id, body);
			}
		},
		async borrar() {
			const id = prompt('ID a borrar:');
			if (id) {
				await borrarFachada(id);
				this.resultado = `Borrado ID: ${id}`;
			}
		},
	},
};
</script>

<style scoped>
button {
	margin: 0.5em;
	padding: 0.5em 1em;
	font-size: 1em;
}
pre {
	background: #f4f4f4;
	padding: 1em;
	border-radius: 5px;
}
</style> */
