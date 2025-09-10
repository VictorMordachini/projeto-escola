const BASE_URL = "http://localhost:8080";

const $ = (sel) => document.querySelector(sel);
const byId = (id) => document.getElementById(id);

async function api(path, opts = {}) {
  const res = await fetch(`${BASE_URL}${path}`, {
    headers: { "Content-Type": "application/json" },
    ...opts
  });
  const text = await res.text();
  let data = null;
  try { data = text ? JSON.parse(text) : null; } catch { data = text; }
  if (!res.ok) {
    const msg = typeof data === "object" ? JSON.stringify(data) : text;
    throw new Error(`HTTP ${res.status}: ${msg || res.statusText}`);
  }
  return data;
}


let editingCourseId = null;
let editingUser = { type: null, id: null };


const cursoForm = byId("cursoForm");
const cursoTitulo = byId("cursoTitulo");
const cursoCarga = byId("cursoCarga");
const cursoTableBody = $("#cursoTable tbody");
const btnCursoSubmit = byId("btnCursoSubmit");
const btnCursoCancelar = byId("btnCursoCancelar");

const usuarioForm = byId("usuarioForm");
const userNome = byId("userNome");
const userCpf = byId("userCpf");
const userTipo = byId("userTipo");
const userTurma = byId("userTurma");
const userCurso = byId("userCurso");
const userTurmas = byId("userTurmas");
const userDisciplinas = byId("userDisciplinas");
const rowAluno = byId("rowAluno");
const rowProfessor = byId("rowProfessor");
const boxTurma = byId("boxTurma");
const btnUsuarioSubmit = byId("btnUsuarioSubmit");
const btnUsuarioCancelar = byId("btnUsuarioCancelar");

const alunoTableBody = $("#alunoTable tbody");
const profTableBody = $("#profTable tbody");


function updateTipoUI() {
  const tipo = userTipo.value;
  if (tipo === "ALUNO") {
    rowAluno.classList.remove("hidden");
    boxTurma.classList.remove("hidden");
    rowProfessor.classList.add("hidden");
    carregarCursosSelect();
  } else if (tipo === "PROFESSOR") {
    rowProfessor.classList.remove("hidden");
    rowAluno.classList.add("hidden");
    boxTurma.classList.add("hidden");
  } else {
    rowAluno.classList.add("hidden");
    rowProfessor.classList.add("hidden");
    boxTurma.classList.add("hidden");
  }
}

userTipo.addEventListener("change", updateTipoUI);


async function carregarCursosSelect() {
  userCurso.innerHTML = `<option value="">-- selecione --</option>`;
  const cursos = await api("/curso");
  cursos.forEach(c => {
    const opt = document.createElement("option");
    opt.value = c.id;
    opt.textContent = `${c.titulo} (${c.cargaHoraria}h)`;
    userCurso.appendChild(opt);
  });
}

async function listarCursos() {
  const cursos = await api("/curso");
  renderCursoTable(cursos);
}

function renderCursoTable(cursos) {
  cursoTableBody.innerHTML = "";
  cursos.forEach(c => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${c.titulo}</td>
      <td>${c.cargaHoraria}</td>
      <td>
        <button onclick="editarCurso('${c.id}','${encodeURIComponent(c.titulo)}',${c.cargaHoraria})">Editar</button>
        <button class="danger" onclick="excluirCurso('${c.id}')">Excluir</button>
      </td>
    `;
    cursoTableBody.appendChild(tr);
  });
}

window.editarCurso = function(id, tituloEncoded, carga) {
  editingCourseId = id;
  cursoTitulo.value = decodeURIComponent(tituloEncoded);
  cursoCarga.value = carga;
  btnCursoSubmit.textContent = "Salvar alterações";
  btnCursoCancelar.classList.remove("hidden");
};

window.excluirCurso = async function(id) {
  if (!confirm("Excluir este curso?")) return;
  try {
    await api(`/curso/${id}`, { method: "DELETE" });
    await listarCursos();
    if (userTipo.value === "ALUNO") await carregarCursosSelect();
  } catch (e) {
    alert(e.message);
  }
};

btnCursoCancelar.addEventListener("click", () => {
  editingCourseId = null;
  cursoForm.reset();
  btnCursoSubmit.textContent = "Cadastrar Curso";
  btnCursoCancelar.classList.add("hidden");
});

cursoForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  const titulo = cursoTitulo.value.trim();
  const cargaHoraria = parseInt(cursoCarga.value, 10);
  if (!titulo || !Number.isFinite(cargaHoraria) || cargaHoraria <= 0) {
    alert("Preencha título e carga horária (> 0).");
    return;
  }
  try {
    if (editingCourseId) {
      await api(`/curso/${editingCourseId}`, {
        method: "PUT",
        body: JSON.stringify({ id: editingCourseId, titulo, cargaHoraria })
      });
    } else {
      await api("/curso", {
        method: "POST",
        body: JSON.stringify({ titulo, cargaHoraria })
      });
    }
    cursoForm.reset();
    editingCourseId = null;
    btnCursoSubmit.textContent = "Cadastrar Curso";
    btnCursoCancelar.classList.add("hidden");
    await listarCursos();
    if (userTipo.value === "ALUNO") await carregarCursosSelect();
  } catch (e) {
    alert(e.message);
  }
});


function parseCsv(s) {
  if (!s) return [];
  return s.split(",").map(x => x.trim()).filter(Boolean);
}

async function listarAlunos() {
  const alunos = await api("/aluno");
  renderAlunoTable(alunos);
}
function renderAlunoTable(alunos) {
  alunoTableBody.innerHTML = "";
  alunos.forEach(a => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${a.nome ?? ""}</td>
      <td>${a.cpf ?? ""}</td>
      <td>${a.turma ?? ""}</td>
      <td>${a.idCurso ?? ""}</td>
      <td>
        <button onclick="editarAluno('${a.id}')">Editar</button>
        <button class="danger" onclick="excluirAluno('${a.id}')">Excluir</button>
      </td>
    `;
    alunoTableBody.appendChild(tr);
  });
}

async function listarProfessores() {
  const profs = await api("/professor");
  renderProfTable(profs);
}
function renderProfTable(profs) {
  profTableBody.innerHTML = "";
  profs.forEach(p => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${p.nome ?? ""}</td>
      <td>${p.cpf ?? ""}</td>
      <td>${(p.turmas || []).join(", ")}</td>
      <td>${(p.disciplinas || []).join(", ")}</td>
      <td>
        <button onclick="editarProfessor('${p.id}')">Editar</button>
        <button class="danger" onclick="excluirProfessor('${p.id}')">Excluir</button>
      </td>
    `;
    profTableBody.appendChild(tr);
  });
}


usuarioForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  const nome = userNome.value.trim();
  const cpfStr = userCpf.value.trim();
  const tipo = userTipo.value;

  if (!nome || !cpfStr || !tipo) {
    alert("Preencha nome, CPF e tipo de usuário.");
    return;
  }
  const cpf = Number(cpfStr);
  if (!Number.isFinite(cpf) || cpfStr.length < 11) {
    alert("CPF inválido (11 dígitos, apenas números).");
    return;
  }

  try {
    if (tipo === "ALUNO") {
      const turma = userTurma.value.trim();
      const idCurso = userCurso.value;
      if (!turma || !idCurso) {
        alert("Para Aluno: informe Turma e selecione um Curso.");
        return;
      }
      const dto = { id: editingUser.id ?? null, nome, cpf, idCurso, turma };

      if (editingUser.id && editingUser.type === "ALUNO") {
        await api(`/aluno/${editingUser.id}`, { method: "PUT", body: JSON.stringify(dto) });
      } else {
        await api("/aluno", { method: "POST", body: JSON.stringify(dto) });
      }
      await listarAlunos();

    } else if (tipo === "PROFESSOR") {
      const turmas = parseCsv(userTurmas.value);
      const disciplinas = parseCsv(userDisciplinas.value);
      const dto = { id: editingUser.id ?? null, nome, cpf, turmas, disciplinas };

      if (editingUser.id && editingUser.type === "PROFESSOR") {
        await api(`/professor/${editingUser.id}`, { method: "PUT", body: JSON.stringify(dto) });
      } else {
        await api("/professor", { method: "POST", body: JSON.stringify(dto) });
      }
      await listarProfessores();
    }

    usuarioForm.reset();
    editingUser = { type: null, id: null };
    btnUsuarioSubmit.textContent = "Cadastrar Usuário";
    btnUsuarioCancelar.classList.add("hidden");
    updateTipoUI();

  } catch (e) {
    alert(e.message);
  }
});

btnUsuarioCancelar.addEventListener("click", () => {
  usuarioForm.reset();
  editingUser = { type: null, id: null };
  btnUsuarioSubmit.textContent = "Cadastrar Usuário";
  btnUsuarioCancelar.classList.add("hidden");
  updateTipoUI();
});

window.editarAluno = async function(id) {
  try {
    const a = await api(`/aluno/${id}`);
    userNome.value = a.nome ?? "";
    userCpf.value = a.cpf ?? "";
    userTipo.value = "ALUNO";
    updateTipoUI();
    await carregarCursosSelect();
    userTurma.value = a.turma ?? "";
    if (a.idCurso) userCurso.value = a.idCurso;
    editingUser = { type: "ALUNO", id };
    btnUsuarioSubmit.textContent = "Salvar alterações";
    btnUsuarioCancelar.classList.remove("hidden");
    window.scrollTo({ top: usuarioForm.offsetTop - 10, behavior: "smooth" });
  } catch (e) { alert(e.message); }
};

window.excluirAluno = async function(id) {
  if (!confirm("Excluir este aluno?")) return;
  try {
    await api(`/aluno/${id}`, { method: "DELETE" });
    await listarAlunos();
  } catch (e) { alert(e.message); }
};

window.editarProfessor = async function(id) {
  try {
    const p = await api(`/professor/${id}`);
    userNome.value = p.nome ?? "";
    userCpf.value = p.cpf ?? "";
    userTipo.value = "PROFESSOR";
    updateTipoUI();
    userTurmas.value = (p.turmas || []).join(", ");
    userDisciplinas.value = (p.disciplinas || []).join(", ");
    editingUser = { type: "PROFESSOR", id };
    btnUsuarioSubmit.textContent = "Salvar alterações";
    btnUsuarioCancelar.classList.remove("hidden");
    window.scrollTo({ top: usuarioForm.offsetTop - 10, behavior: "smooth" });
  } catch (e) { alert(e.message); }
};

window.excluirProfessor = async function(id) {
  if (!confirm("Excluir este professor?")) return;
  try {
    await api(`/professor/${id}`, { method: "DELETE" });
    await listarProfessores();
  } catch (e) { alert(e.message); }
};

(async function init() {
  try {
    await listarCursos();
    await listarAlunos();
    await listarProfessores();
    updateTipoUI();
  } catch (e) {
    alert("Erro ao iniciar: " + e.message);
  }
})();