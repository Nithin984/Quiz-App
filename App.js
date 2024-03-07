

import { useEffect, useState } from "react";
let score=null;

const dataHandler = { quizName: null, answers: null };
export default function App() {
  const [listOfQuestions, setListOfQuestions] = useState([]);
  dataHandler.answers = Array(listOfQuestions.length).fill(null);
  useEffect(() => {
    async function fetchData() {
      try {
        const response = await fetch("http://localhost:8080/Quiz/get/2", {
          method: "GET",
        });
        const data = await response.json();
        console.log(data)
        // dataHandler.quizName = data[];
        setListOfQuestions(()=>[...data]);
      } catch (error) {
        console.log("An Error Occurred ", error);
      }
    }
    fetchData();
  }, []);
  return (
    <QuizBox
      listOfQuestions={listOfQuestions}
      answers={dataHandler.answers}
      quizName={dataHandler.quizName}
    />
  );
}

function QuizBox({ listOfQuestions, answers, quizName }) {
  const [index, setIndex] = useState(0);
  const [scoreFacotor,setScoreFactor]=useState(false);
  
  function submitData() {
    async function postData() {
      try {
        const response = await fetch("http://localhost:8080/Quiz/submit/2", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(answers),
        });
        score = await response.json();
        setScoreFactor(true)
        console.log(score);
      } catch (error) {
        // Handle the error
        console.error("An error occurred:", error);
      }
    }

    postData();
  }
  return listOfQuestions.length !== 0 && scoreFacotor===false ? (
    <div className="quizbox">
      <h1>{quizName}</h1>
      <ListOfQuestions
        eachQuestion={listOfQuestions[index]}
        index={index}
        key={index}
        answers={answers}
      />
      <div style={{ display: "flex", justifyContent: "space-between" }}>
        <button
          onClick={() =>
            setIndex(index > 0 ? (currentValue) => currentValue - 1 : index)
          }
        >
          Prev
        </button>
        <p>
          {index + 1}/{listOfQuestions.length}
        </p>
        <button
          onClick={() =>
            setIndex(
              index < listOfQuestions.length - 1
                ? (currentValue) => currentValue + 1
                : index
            )
          }
        >
          Next
        </button>
      </div>
      <button
        style={{
          backgroundColor: "green",
          width: "100%",
          padding: "14px 3px",
        }}
        onClick={() => submitData()}
      >
        Submit
      </button>
    </div>
  ) : (
    scoreFacotor===true? <p className="display">Score: {`${score}/${listOfQuestions.length}`}</p>:
    <p className="display">
      We are trying to fetch data please wait ...
    </p>
  );
}
function ListOfQuestions({ eachQuestion, index, answers }) {
  const [ticked, setTicked] = useState(null);
  const options = Array(4)
    .fill(1)
    .map((eachVal, indexNo) => eachQuestion[`option${indexNo + 1}`]);

  if (ticked === null) {
    if (answers[index] !== null) {
      let returnVal =
        options.findIndex((eachOpt) => eachOpt === answers[index]) + 1;
      setTicked(returnVal);
    }
  } else {
    answers[index] = eachQuestion[`option${ticked}`];
  }
  console.log(answers);

  return (
    <div>
      <h1>{eachQuestion.qname}</h1>
      <ul>
        {options.map((eachOption, index) => (
          <li key={index + 1}>
            <input
              type="radio"
              name={eachQuestion.question}
              checked={ticked === index + 1}
              onChange={() => setTicked(index + 1)}
            />
            {eachOption}
          </li>
        ))}
      </ul>
    </div>
  );
}
